package dev.dubhe.gugle.carpet.tools;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;

import java.util.Locale;

public class SimpleInGameCalculator {
    public static void handleChat(MinecraftServer server, String msg) {
        if (msg.startsWith("=")) return;
        server.getPlayerList().broadcastSystemMessage(SimpleInGameCalculator.calculate(msg), false);
    }

    public static Component calculate(String expression) {
        if (expression.startsWith("==")) expression = expression.substring(2);
        try {
            double result = new Parser(expression).evaluate();
            return Component.literal("=%f".formatted(result)).withStyle(ChatFormatting.GRAY);
        } catch (ExpressionException e) {
            return Component.literal("Illegal expression: %s".formatted(e.getMessage()))
                .withStyle(ChatFormatting.RED);
        }
    }

    private static final class ExpressionException extends RuntimeException {
        private ExpressionException(String message) {
            super(message);
        }
    }

    private static final class Parser {
        private final String source;
        private int position;

        private Parser(String source) {
            this.source = source;
        }

        private double evaluate() {
            if (this.source.isBlank()) throw new ExpressionException("empty expression");
            double value = this.parseAdditive();
            this.skipWhitespace();
            if (this.position < this.source.length()) {
                throw new ExpressionException("unexpected '" + this.source.charAt(this.position) + "' at " + this.position);
            }
            if (Double.isNaN(value) || Double.isInfinite(value)) throw new ExpressionException("invalid result");
            return value;
        }

        private double parseAdditive() {
            double value = this.parseMultiplicative();
            while (true) {
                this.skipWhitespace();
                char c = this.peek();
                if (c == '+') {
                    this.position++;
                    value += this.parseMultiplicative();
                } else if (c == '-') {
                    this.position++;
                    value -= this.parseMultiplicative();
                } else {
                    return value;
                }
            }
        }

        private double parseMultiplicative() {
            double value = this.parseUnary();
            while (true) {
                this.skipWhitespace();
                char c = this.peek();
                if (c == '*') {
                    this.position++;
                    value *= this.parseUnary();
                } else if (c == '/') {
                    this.position++;
                    value /= this.parseUnary();
                } else if (c == '%') {
                    this.position++;
                    value %= this.parseUnary();
                } else {
                    return value;
                }
            }
        }

        private double parseUnary() {
            this.skipWhitespace();
            char c = this.peek();
            if (c == '-') {
                this.position++;
                return -this.parseUnary();
            }
            if (c == '+') {
                this.position++;
                return this.parseUnary();
            }
            return this.parsePower();
        }

        private double parsePower() {
            double base = this.parsePrimary();
            this.skipWhitespace();
            if (this.peek() == '^') {
                this.position++;
                return Math.pow(base, this.parseUnary());
            }
            return base;
        }

        private double parsePrimary() {
            this.skipWhitespace();
            char c = this.peek();
            if (c == '(') {
                this.position++;
                double value = this.parseAdditive();
                this.skipWhitespace();
                if (this.peek() != ')') throw new ExpressionException("missing ')'");
                this.position++;
                return value;
            }
            if (c == '\0') throw new ExpressionException("unexpected end of expression");
            if (Character.isDigit(c) || c == '.') return this.parseNumber();
            if (Character.isLetter(c) || c == '_') return this.parseIdentifier();
            throw new ExpressionException("unexpected '" + c + "' at " + this.position);
        }

        private double parseNumber() {
            int start = this.position;
            while (this.position < this.source.length()) {
                char c = this.source.charAt(this.position);
                if (Character.isDigit(c) || c == '.') {
                    this.position++;
                } else if ((c == 'e' || c == 'E') && this.position + 1 < this.source.length()
                    && (Character.isDigit(this.source.charAt(this.position + 1))
                    || ((this.source.charAt(this.position + 1) == '+' || this.source.charAt(this.position + 1) == '-')
                    && this.position + 2 < this.source.length()
                    && Character.isDigit(this.source.charAt(this.position + 2))))) {
                    this.position += 2;
                } else {
                    break;
                }
            }
            String text = this.source.substring(start, this.position);
            try {
                return Double.parseDouble(text);
            } catch (NumberFormatException e) {
                throw new ExpressionException("invalid number '" + text + "'");
            }
        }

        private double parseIdentifier() {
            int start = this.position;
            while (this.position < this.source.length()) {
                char c = this.source.charAt(this.position);
                if (Character.isLetterOrDigit(c) || c == '_') this.position++;
                else break;
            }
            String name = this.source.substring(start, this.position).toLowerCase(Locale.ROOT);
            this.skipWhitespace();
            if (this.peek() != '(') return this.constant(name);
            this.position++;
            double first = this.parseAdditive();
            this.skipWhitespace();
            if (this.peek() == ',') {
                this.position++;
                double second = this.parseAdditive();
                this.skipWhitespace();
                if (this.peek() == ',') {
                    this.position++;
                    double third = this.parseAdditive();
                    this.skipWhitespace();
                    if (this.peek() != ')') throw new ExpressionException("missing ')'");
                    this.position++;
                    return this.applyTernary(name, first, second, third);
                }
                if (this.peek() != ')') throw new ExpressionException("missing ')'");
                this.position++;
                return this.applyBinary(name, first, second);
            }
            if (this.peek() != ')') throw new ExpressionException("missing ')'");
            this.position++;
            return this.applyUnary(name, first);
        }

        private double constant(String name) {
            return switch (name) {
                case "pi" -> Math.PI;
                case "e" -> Math.E;
                default -> throw new ExpressionException("unknown symbol '" + name + "'");
            };
        }

        private double applyUnary(String name, double value) {
            return switch (name) {
                case "abs" -> Math.abs(value);
                case "sqrt" -> Math.sqrt(value);
                case "cbrt" -> Math.cbrt(value);
                case "exp" -> Math.exp(value);
                case "ln" -> Math.log(value);
                case "log" -> Math.log10(value);
                case "log10" -> Math.log10(value);
                case "log2" -> Math.log(value) / Math.log(2.0D);
                case "sin" -> Math.sin(value);
                case "cos" -> Math.cos(value);
                case "tan" -> Math.tan(value);
                case "asin" -> Math.asin(value);
                case "acos" -> Math.acos(value);
                case "atan" -> Math.atan(value);
                case "sinh" -> Math.sinh(value);
                case "cosh" -> Math.cosh(value);
                case "tanh" -> Math.tanh(value);
                case "ceil" -> Math.ceil(value);
                case "floor" -> Math.floor(value);
                case "round" -> (double) Math.round(value);
                case "signum" -> Math.signum(value);
                case "todegrees" -> Math.toDegrees(value);
                case "toradians" -> Math.toRadians(value);
                case "fact" -> Parser.factorial(value);
                default -> throw new ExpressionException("unknown function '" + name + "'");
            };
        }

        private double applyBinary(String name, double first, double second) {
            return switch (name) {
                case "pow" -> Math.pow(first, second);
                case "min" -> Math.min(first, second);
                case "max" -> Math.max(first, second);
                case "atan2" -> Math.atan2(first, second);
                case "hypot" -> Math.hypot(first, second);
                case "mod" -> first % second;
                case "log" -> Math.log(second) / Math.log(first);
                case "round" -> Parser.roundTo(first, second);
                default -> throw new ExpressionException("unknown function '" + name + "'");
            };
        }

        private double applyTernary(String name, double first, double second, double third) {
            if (!name.equals("if")) throw new ExpressionException("unknown function '" + name + "'");
            return first != 0.0D ? second : third;
        }

        private char peek() {
            return this.position < this.source.length() ? this.source.charAt(this.position) : '\0';
        }

        private void skipWhitespace() {
            while (this.position < this.source.length() && Character.isWhitespace(this.source.charAt(this.position))) {
                this.position++;
            }
        }

        private static double factorial(double value) {
            if (value < 0.0D || value != Math.floor(value) || value > 170.0D) {
                throw new ExpressionException("fact() requires an integer between 0 and 170");
            }
            double result = 1.0D;
            for (int i = 2; i <= (int) value; i++) result *= i;
            return result;
        }

        private static double roundTo(double value, double digits) {
            double factor = Math.pow(10.0D, digits);
            return Math.round(value * factor) / factor;
        }
    }
}
