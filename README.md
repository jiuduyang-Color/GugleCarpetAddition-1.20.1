# GugleCarpetAddition — Minecraft 1.20.1 Forge 移植版

[ [中文](README.md) | [English](#english) ]

> **GCA（Gugle's Carpet Addition）在 Minecraft 1.20.1 + Forge 上的非官方移植版**
>
> - 移植作者（Port author）：**jiuduyang**
> - 原作者（Original author）：**Gugle（Gu-ZT）**
> - 原项目：<https://github.com/Gu-ZT/gugle-carpet-addition>
> - 开源许可：**MIT**（与原项目一致）

---

## 简介

GCA 是 [Carpet](https://github.com/gnembon/fabric-carpet) 的功能扩展模组，为服务器提供**假人（Bot）管理、地标、待办清单、快捷坐标、白名单/封禁管理**等一整套服务端工具。

原项目基于 Fabric 开发并提供多版本构建，但 **Forge 端只发布到 1.18.2 为止**。本仓库是基于原项目 1.20.1 分支源码移植的 **Minecraft 1.20.1 Forge** 版本，功能与原版保持一致。

## 环境要求

| 项目 | 版本 |
| --- | --- |
| Minecraft | 1.20.1 |
| Forge | 47.x（开发时使用 47.2.0） |
| Java | 17 |
| 必需前置 | **Carpet 的 Forge 移植版**（modId: `carpet`，1.4.112 或更高） |

### 关于前置模组 Carpet

Fabric 版 Carpet 无法在 Forge 上运行，因此本移植版依赖 **Carpet 的 Forge 移植版**：

- 名称：Carpet: NeoForged（Forge Backport）
- 文件：`forge-carpet-1.20.1-1.0.8+v251027.jar`
- 作者：chililisoup
- 来源：<https://github.com/chililisoup/neoforge-carpet> / <https://modrinth.com/mod/neoforge-carpet>
- 许可：MIT

> 前置模组不随本仓库分发，请从上述来源自行下载。

## 安装

1. 安装 Minecraft 1.20.1 + Forge 47.x 服务端（或客户端）。
2. 把 `forge-carpet-1.20.1-*.jar` 放入 `mods/`。
3. 把 `gugle-carpet-addition-2.12.8-forge-1.20.1.jar` 放入 `mods/`。
4. 启动服务器。

本模组为**服务端模组**，客户端可以不安装（已在 `mods.toml` 中设置 `displayTest="IGNORE_SERVER_VERSION"`，原版客户端可正常进入服务器）。

## 功能一览

所有功能通过 `/carpet` 指令开关，分类标签为 `GCA`。

### 假人 / Bot

| 规则 | 说明 |
| --- | --- |
| `openFakePlayerInventory` | 右键假人打开其背包 |
| `openFakePlayerEnderChest` | 潜行右键假人打开其末影箱 |
| `fakePlayerResident` | 退出存档时保留假人 |
| `fakePlayerReloadAction` | 重载存档时保留假人动作 |
| `fakePlayerAutoRespawn` | 假人死亡后自动重生（`spawn` / `death` / `setting`） |
| `fakePlayerAutoReplenishment` | 假人自动补货 |
| `fakePlayerAutoReplenishmentFormShulkerBox` | 支持从潜影盒补货 |
| `fakePlayerAutoFish` | 假人自动钓鱼 |
| `fakePlayerAutoReplaceTool` | 假人工具耐久过低时自动更换 |
| `fakePlayerToolDamagedNotification` | 工具损坏 / 补货失败时全服提示 |
| `fakePlayerPrefixName` / `fakePlayerSuffixName` | 假人名字前后缀 |
| `fakePlayerForceOfflineUUID` | 强制假人使用离线 UUID |

### 指令

| 指令 | 说明 |
| --- | --- |
| `/bot` | 假人管理菜单：保存 / 加载 / 分组 / 动作 / 自定义按钮 |
| `/todo` | 待办清单 |
| `/loc` | 地标管理（含下界坐标换算、Xaero 小地图路径点） |
| `/here` | 广播自己的坐标 |
| `/whereis` / `/vris` | 查询玩家位置 |
| `/wlist` | 白名单管理 |
| `/blist` | 封禁名单管理 |
| `/sop` | 快速获取 OP |

### 其他

| 规则 | 说明 |
| --- | --- |
| `betterFenceGatePlacement` | 放置栅栏门时继承被点击栅栏门的状态 |
| `betterWoodStrip` | 只有名字含「去皮 / Strip」的斧头才能去皮 |
| `betterSignInteraction` | 右键告示牌时与其附着方块交互 |
| `betterItemFrameInteraction` | 右键展示框时与其附着方块交互 |
| `betterQuickCrafting` | 快速合成时在背包保留一份物品 |
| `simpleInGameCalculator` | 游戏内计算器（聊天栏输入 `==表达式`） |
| `fastPingFriend` | 快速 ping 好友（`@ 玩家 消息`） |
| `welcomePlayer` | 玩家进服欢迎语 |
| `wanderingTraderSpawnRemind` | 流浪商人刷新提醒 |
| `fixedEndCrystalSync` | 修复末地水晶同步 |
| `gcaPageSize` | 列表每页条目数 |

完整规则说明（含截图）请参考原项目文档：<https://github.com/Gu-ZT/gugle-carpet-addition/blob/releases/preprocess/README_cn.md>

## 从源码构建

```bash
./gradlew build
```

产物位于 `build/libs/gugle-carpet-addition-2.12.8-forge-1.20.1.jar`。

依赖已随仓库提供：

- `libs/forge-carpet-1.20.1-1.0.8.jar`（编译期前置，MIT）
- `libs/MixinExtras-0.4.1.jar`（编译期注解处理，MIT）

开发环境运行：

```bash
./gradlew runServer     # 启动开发用服务端
./gradlew runClient     # 启动开发用客户端
```

## 移植改动摘要

与上游 1.20.1 源码相比，本移植版做了以下改动（详见 `文档/移植技术报告.md`）：

1. 构建系统由 Fabric Loom + 多版本预处理器改为 **ForgeGradle 6 + MixinGradle + Parchment**，单目标 1.20.1。
2. 模组入口由 Fabric `ModInitializer` 改为 Forge `@Mod`。
3. 加载器判断 `FabricLoader#isModLoaded` 改为 `ModList#get().isLoaded`。
4. 元数据由 `fabric.mod.json` 改为 `META-INF/mods.toml`，Mixin 配置补充 `refmap`。
5. 修复源码中面向 1.21.1 主分支写法、无法在 1.20.1 编译/运行的 API 差异。
6. 移除 GPL 授权的 `jep` 依赖，改为内置的自研表达式解析器（保持 `simpleInGameCalculator` 功能）。
7. 按仓库要求，模组 Java 源码内**不含任何注释**。

## 已知差异与说明

- 假人、`/player` 等能力来自前置 Carpet，行为与上游一致。
- `simpleInGameCalculator` 支持常用数学函数与常量（`sqrt`、`sin`、`log`、`pow`、`min`、`max`、`pi`、`e` 等），不支持 JEP 的复数运算。
- 开发环境（`runServer`）启动时会有一条 `Reference map 'gca.refmap.json' ... could not be read` 警告，这是 Forge 开发环境的正常现象，正式构建的 jar 中 refmap 正常。

## 许可

本项目遵循原项目的 **MIT** 许可。原始版权归 Gugle（Gu-ZT）所有，移植部分版权归 jiuduyang 所有。详见 `LICENSE` 与 `文档/许可与来源说明.md`。

---

## English

**Unofficial Minecraft 1.20.1 Forge port of GugleCarpetAddition (GCA).**

- Port author: **jiuduyang**
- Original author: **Gugle (Gu-ZT)** — <https://github.com/Gu-ZT/gugle-carpet-addition>
- License: **MIT**

GCA is a server-side addon for [Carpet](https://github.com/gnembon/fabric-carpet) that provides fake-player (bot) management, waypoints, todo lists, position broadcasting and whitelist/ban management. The upstream project only shipped Forge builds up to 1.18.2; this repository ports the 1.20.1 source to **Minecraft 1.20.1 Forge**.

### Requirements

- Minecraft 1.20.1, Forge 47.x, Java 17
- **Carpet Forge port** (`modId: carpet`, ≥ 1.4.112): [`forge-carpet-1.20.1-1.0.8`](https://modrinth.com/mod/neoforge-carpet) by chililisoup (MIT). Not redistributed here.

### Build

```bash
./gradlew build
```

Output: `build/libs/gugle-carpet-addition-2.12.8-forge-1.20.1.jar`.

### Porting changes

1. Fabric Loom + preprocessor → ForgeGradle 6 + MixinGradle + Parchment (single target 1.20.1).
2. Fabric `ModInitializer` → Forge `@Mod`.
3. `FabricLoader#isModLoaded` → `ModList#get().isLoaded`.
4. `fabric.mod.json` → `META-INF/mods.toml`; mixin config now declares a refmap.
5. Fixed APIs that were written for the 1.21.1 main branch and do not exist in 1.20.1.
6. Dropped the GPL-licensed `jep` dependency; `simpleInGameCalculator` now uses a built-in expression parser.
7. No comments in the mod's Java sources.

### License

MIT. Original copyright (c) 2022 Gugle; port copyright (c) 2025-2026 jiuduyang.
