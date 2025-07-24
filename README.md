# 🌍 Custom Biome Test

This project demonstrates how to add **custom biomes to Minecraft** without relying on biome-modifying libraries like **TerraBlender**.

---

## ✅ Features

- Custom biome registration from scratch
- (Should be) Compatible with **Minecraft Forge** (Not Tested), **NeoForge** (Not Tested), and **Fabric**
- Tested with **Fabric** using **Mojang mappings (mojmap)**

---

## 🔧 Required Technologies

| Technology                 | Purpose                                                                 |
|----------------------------|-------------------------------------------------------------------------|
| [Mixin](https://github.com/SpongePowered/Mixin) | Modify vanilla Minecraft classes at runtime without base edits |
| Access Wideners (AW)       | Required for **Fabric** to expose protected/private fields or methods   |
| Access Transformers (AT)   | Used in **Forge/NeoForge** to modify method/field access levels         |

---

## 🧪 Tested Environment

- **Mod Loader**: Fabric
- **Mappings**: Mojang (mojmap)
- **Minecraft Version**: 1.21.1

---

## ⚠️ Disclaimer

> This is **not a professional or complete solution** for adding custom biomes.  
> It only supports **simple biome registration** and is intended for experimentation or learning purposes.

---

## 💖 Support / Donation

If you find this project helpful and want to support:

**[Buy Me a Coffee ☕](https://www.buymeacoffee.com/CoolerProMC)**

---

## 🚧 Work In Progress

This is an experimental/test project — not meant for production or general use yet.
