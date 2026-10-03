# libs

本目录下的 jar 仅用于**编译期**，不会被打进成品模组，也不会随仓库之外分发。

| 文件 | 用途 | 许可 | 来源 |
| --- | --- | --- | --- |
| `forge-carpet-1.20.1-1.0.8.jar` | 编译期前置。提供 `carpet.*` 全部 API（`CarpetExtension`、`EntityPlayerMPFake`、`EntityPlayerActionPack`、`api.settings.*`、`utils.*` 等） | MIT | [Carpet: NeoForged](https://modrinth.com/mod/neoforge-carpet) by chililisoup |
| `MixinExtras-0.4.1.jar` | 编译期注解处理。模组大量使用 `@WrapOperation`、`@Local` 等 MixinExtras 注解 | MIT | [MixinExtras](https://github.com/LlamaLad7/MixinExtras) by LlamaLad7 |

## 运行期说明

- `forge-carpet` 是**必需前置**，玩家需要自行把 `forge-carpet-1.20.1-*.jar` 放进 `mods/`。
- `MixinExtras` **不需要**玩家单独安装：前置 `forge-carpet` 已通过 Forge JarJar 内嵌 `mixinextras-forge-0.4.1.jar`。

## 更新方式

升级前置 Carpet 时，替换 `forge-carpet` 的 jar 并同步修改 `build.gradle` 中：

```groovy
implementation fg.deobf("blank:forge-carpet-1.20.1-1.0.8:1.0.8")
```

如果新版前置内嵌的 MixinExtras 版本发生变化，请同步替换 `MixinExtras-<version>.jar` 并更新：

```groovy
compileOnly files('libs/MixinExtras-0.4.1.jar')
annotationProcessor files('libs/MixinExtras-0.4.1.jar')
```
