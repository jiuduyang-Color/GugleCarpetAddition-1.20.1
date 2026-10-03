# GugleCarpetAddition — Curtain 附属版（Minecraft 1.20.1 Forge）

[ [中文](README.md) | [English](#english) ]

> **GCA（Gugle's Carpet Addition）在 Curtain（窗帘）+ Minecraft 1.20.1 Forge 上的移植版**
>
> - 移植作者（Port author）：**jiuduyang**
> - 原作者（Original author）：**Gugle（Gu-ZT）**
> - 原项目：<https://github.com/Gu-ZT/gugle-carpet-addition>
> - 开源许可：**MIT**（与原项目一致）

---

## 这是什么

原版 GCA 是 Fabric 端 [Carpet](https://github.com/gnembon/fabric-carpet) 的功能扩展。Forge 端没有 Carpet，而是由同一作者的 [**Curtain（窗帘）**](https://github.com/Gu-ZT/Curtain) 提供等价能力。

本模组是一个 **Curtain 附属模组（addon）**：通过 Curtain 官方的 `ICurtain` 附属接口接入，把 GCA 独有的功能补到 Curtain 上。

> 上游 GCA 的 Forge 构建只到 1.18.2；1.19 之后没有 Forge 版本。本仓库补上 **1.20.1 Forge + Curtain** 这一块空白。

## 环境要求

| 项目 | 版本 |
| --- | --- |
| Minecraft | 1.20.1 |
| Forge | 47.x（开发时使用 47.2.0） |
| Java | 17 |
| 必需前置 | **Curtain（窗帘）1.3.2**（modId: `curtain`） |

### 关于前置 Curtain

- 名称：Curtain（窗帘）
- 文件：`curtain-mc1.20.1-1.3.2.jar`
- 作者：Gugle（Gu-ZT）
- 下载：<https://modrinth.com/mod/curtain>
- 许可：**LGPL-2.1**

> 前置模组不随本仓库分发，请自行下载。
>
> 注意：本模组**不能**配合 `forge-carpet` 使用，二者包结构与模组 ID 完全不同。

## 安装

1. 安装 Minecraft 1.20.1 + Forge 47.x 服务端。
2. 把 `curtain-mc1.20.1-1.3.2.jar` 放入 `mods/`。
3. 把 `gugle-carpet-addition-2.12.8-curtain-1.20.1.jar` 放入 `mods/`。
4. 启动服务器。

本模组为**服务端模组**，客户端可以不安装（`mods.toml` 中已设置 `displayTest="IGNORE_SERVER_VERSION"`）。

## 规则开关方式

Curtain 的规则指令与 Fabric Carpet **不同**，不是 `/carpet <规则> <值>`，而是：

```
/curtain                       # 打开规则分类菜单
/curtain setValue <规则> <值>   # 临时修改
/curtain setDefault <规则> <值> # 设为默认（写入存档）
```

GCA 的规则分类标签为 **`GCA`**，另有 `Experimental` 分类。

## 功能一览（GCA 独有部分）

### 指令

| 指令 | 说明 |
| --- | --- |
| `/bot` | 假人管理：保存 / 加载 / 分组 / 动作 / 自定义按钮 |
| `/todo` | 待办清单 |
| `/loc` | 地标管理（含下界坐标换算与 Xaero 小地图路径点） |
| `/here` | 广播自己的坐标 |
| `/whereis` / `/vris` | 查询玩家位置 |
| `/wlist` | 白名单管理 |
| `/blist` | 封禁名单管理 |
| `/sop` | 快速获取 OP |

### 规则

| 规则 | 默认 | 说明 |
| --- | --- | --- |
| `openRealPlayerInventory` | false | 右键真人玩家打开其背包 |
| `fakePlayerAutoRespawn` | false | 假人死亡/被 kill 后自动重生（`spawn` / `death` / `setting`） |
| `commandBot` | ops | `/bot` 权限 |
| `commandBotAction` | ops | `/bot action` 权限 |
| `commandBotController` | ops | `/bot controller` 权限 |
| `commandTodo` | ops | `/todo` 权限 |
| `commandHere` | ops | `/here` 权限 |
| `commandWhereis` | ops | `/whereis` 权限 |
| `commandLoc` | ops | `/loc` 权限 |
| `commandWlist` | false | `/wlist` 开关 |
| `commandBlist` | false | `/blist` 开关 |
| `commandSop` | false | `/sop` 开关 |
| `commandSeed` | vanilla | 修改 `/seed` 所需权限（支持 `vanilla`） |
| `betterItemFrameInteraction` | false | 右键展示框时与其附着方块交互 |
| `betterQuickCrafting` | false | 快速合成时在背包保留一份物品 |
| `simpleInGameCalculator` | false | 聊天栏输入 `==表达式` 做计算 |
| `fastPingFriend` | false | `@ 玩家 消息` 快速提醒 |
| `welcomePlayer` | false | 玩家进服欢迎语 |
| `wanderingTraderSpawnFailedWarning` | false | 流浪商人生成失败提醒 |
| `wanderingTraderSpawnRemind` | false | 流浪商人生成提醒 |
| `fixedEndCrystalSync` | false | 修复末地水晶同步 |
| `qnmdLC` | -1 | 设置 LC 高度值 |
| `gcaPageSize` | 8 | 列表每页条目数 |

## 与上游 GCA 的差异（重要）

Curtain **已经内置了上游 GCA 的以下 11 项功能**，因此本移植版**不再重复实现**，请直接使用 Curtain 自带的同名规则：

| 已由 Curtain 提供 | Curtain 规则名 |
| --- | --- |
| 打开假人背包 | `openFakePlayerInventory` |
| 打开假人末影箱 | `openFakePlayerEnderChest` |
| 退出存档保留假人 | `fakePlayerResident` |
| 假人自动补货 | `fakePlayerAutoReplenishment` |
| 假人自动更换工具 | `fakePlayerAutoReplaceTool` |
| 假人自动钓鱼 | `fakePlayerAutoFish` |
| 假人名称前缀 / 后缀 | `fakePlayerNamePrefix` / `fakePlayerNameSuffix` |
| 栅栏门状态继承 | `betterFenceGatePlacement` |
| 去皮限制 | `betterWoodStrip` |
| 告示牌穿透交互 | `betterSignInteraction` |

被移除的还有：`fakePlayerAutoReplenishmentFormShulkerBox`、`fakePlayerToolDamagedNotification`、`fakePlayerForceOfflineUUID`、`fakePlayerReloadAction`、`commandTransfer`、`commandTick`（后两者在 1.20.1 本就不适用）。

**重复注册同名规则会导致冲突，因此这是必要的取舍，不是功能缺失。**

其余说明：

- `/bot controller` 的自定义按钮面板依附于 GCA 自己的容器界面，目前仅在**真人玩家**的背包 / 末影箱界面生效；假人背包由 Curtain 自己实现。
- `simpleInGameCalculator` 使用内置表达式解析器（支持 `+ - * / % ^`、括号、`pi`/`e`、`sqrt`/`sin`/`log`/`pow`/`min`/`max` 等常用函数），不使用 GPL 授权的 JEP 库。

## 从源码构建

```bash
./gradlew build
```

产物：

- `build/libs/gugle-carpet-addition-2.12.8-curtain-1.20.1.jar` ← **成品（已内置 MixinExtras）**
- `build/libs/gugle-carpet-addition-2.12.8-curtain-1.20.1-dev.jar` ← 未打包依赖的中间产物

`libs/curtain-mc1.20.1-1.3.2.jar` 为编译期前置。

开发环境运行：

```bash
./gradlew runServer
```

## 实现要点

1. 通过 Curtain 官方附属接口 `dev.dubhe.curtain.ICurtain` + `Curtain.addSubMod(...)` 接入。
2. 规则体系从 Carpet 的 `@Rule(categories, options, validators, conditions)` 改写为 Curtain 的 `@Rule(categories, suggestions, validators, serializedName)`，校验器由 `Validator<T>`（返回值）改为 `IValidator<T>`（返回布尔）。
3. 生命周期改用 Forge 事件：`ServerAboutToStartEvent`（注册指令 / 初始化配置，优先级 `LOWEST` 以晚于 Curtain）、`PlayerEvent.PlayerLoggedInEvent`。
4. 翻译键改为 Curtain 的 `curtain.rules.<名字>.name/.desc` 与 `curtain.categories.<分类>`。
5. 假人创建改用 Curtain 的 `EntityPlayerMPFake.createFakePlayer(...)`。
6. **Curtain 不内置 MixinExtras**，本模组通过 Forge JarJar 将其打包进成品 jar。

详细过程见 `文档/移植技术报告.md`。

## 许可

本项目遵循原项目的 **MIT** 许可；原始版权归 Gugle（Gu-ZT），移植部分版权归 jiuduyang。

前置 **Curtain 为 LGPL-2.1**，本仓库不包含其代码，仅在运行时依赖。

详见 `LICENSE` 与 `文档/许可与来源说明.md`。

---

## English

**Curtain addon port of GugleCarpetAddition (GCA) for Minecraft 1.20.1 Forge.**

- Port author: **jiuduyang**
- Original author: **Gugle (Gu-ZT)**
- License: **MIT**

### What it is

GCA is a server-side addon for Carpet. On Forge, the equivalent core is [**Curtain**](https://github.com/Gu-ZT/Curtain) (same author). This mod is a **Curtain addon** registered through Curtain's official `ICurtain` API, adding the GCA-only features that Curtain does not ship.

### Requirements

- Minecraft 1.20.1, Forge 47.x, Java 17
- **Curtain 1.3.2** (`modId: curtain`) — <https://modrinth.com/mod/curtain> (LGPL-2.1, not redistributed here)

> This mod is **not** compatible with `forge-carpet`; the packages and mod ids differ completely.

### Rules command

Curtain uses `/curtain setValue <rule> <value>` and `/curtain setDefault <rule> <value>` (not `/carpet ...`). GCA rules live under the `GCA` category.

### Features

Commands: `/bot`, `/todo`, `/loc`, `/here`, `/whereis`, `/wlist`, `/blist`, `/sop`.
Rules: `openRealPlayerInventory`, `fakePlayerAutoRespawn`, `command*` permissions, `betterItemFrameInteraction`, `betterQuickCrafting`, `simpleInGameCalculator`, `fastPingFriend`, `welcomePlayer`, `wanderingTrader*`, `fixedEndCrystalSync`, `qnmdLC`, `gcaPageSize`.

### Deliberate omissions

Curtain already implements 11 upstream GCA features (`openFakePlayerInventory`, `openFakePlayerEnderChest`, `fakePlayerResident`, `fakePlayerAutoReplenishment`, `fakePlayerAutoReplaceTool`, `fakePlayerAutoFish`, `fakePlayerNamePrefix/Suffix`, `betterFenceGatePlacement`, `betterWoodStrip`, `betterSignInteraction`). Registering duplicate rule names would conflict, so this port does not re-implement them.

### Build

```bash
./gradlew build
```

Output: `build/libs/gugle-carpet-addition-2.12.8-curtain-1.20.1.jar` (MixinExtras is bundled via Forge JarJar).

### License

MIT. Original copyright (c) 2022 Gugle; port copyright (c) 2025-2026 jiuduyang. Curtain itself is LGPL-2.1 and is only a runtime dependency.
