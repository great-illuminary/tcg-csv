# TCGPlayer Pricing information

![CI](https://github.com/great-illuminary/tcg-data-pricing/actions/workflows/build.yml/badge.svg)
![License](https://img.shields.io/github/license/great-illuminary/tcg-data-pricing)
![Last Release](https://img.shields.io/github/v/release/great-illuminary/tcg-data-pricing)

[
![Discord](https://img.shields.io/badge/Discord-Lorcana_Manager-blue)
](https://discord.gg/cd4hRF2PXm)

![badge](https://img.shields.io/badge/json-kotlin-green)
![badge](https://img.shields.io/badge/android-blue)
![badge](https://img.shields.io/badge/ios-white)
![badge](https://img.shields.io/badge/js-yellow)
![badge](https://img.shields.io/badge/jvm-red)
![badge](https://img.shields.io/badge/linux-blue)
![badge](https://img.shields.io/badge/windows-blueviolet)
![badge](https://img.shields.io/badge/mac-orange)

Holding lorcana data only

# Integration

```gradle
implementation("eu.codlab:lorcana-data:$version")
```

This will work on the following platforms :
- Mobile (Android/iOS)
- Web (js)
- Native (MacOS/Linux/Windows)
- JVM (MacOS/Linux/Windows)

## Usage
RequestLoader gives all the various method to access the information:

- `RequestLoader.categories()`
- `RequestLoader.groups(categoryId)`
- `RequestLoader.products(categoryId, groupId)`
- `RequestLoader.prices(categoryId, groupId)`

# Structure

**Categories**

- **categoryId**: Int
- **name**: String
- **modifiedOn**: String
- **displayName**: String
- **seoCategoryName**: String
- **categoryDescription**: String
- **categoryPageTitle**: String
- **sealedLabel**: String
- **nonSealedLabel**: String
- **conditionGuideUrl**: String
- **isScannable**: Boolean
- **popularity**: Long
- **isDirect**: Boolean

**Group**

- **groupId**: Int
- **name**: String
- **abbreviation**: String
- **isSupplemental**: Boolean
- **publishedOn**: String
- **modifiedOn**: String
- **categoryId**: Int

**Product**

- **productId**: Int
- **categoryId**: Int
- **groupId**: Int
- **number**: Int

**Price**

- **productId**: Int
- **lowPrice**: Double
- **midPrice**: Double
- **highPrice**: Double
- **marketPrice**: Double
- **directLowPrice**: Double?
- **subTypeName**: String
