# IR Control 2

**IR Control 2** - bu Android uchun infraqizil (IR) qurilmalarni boshqarish ilovasi. Jetpack Compose, Clean Architecture va MVVM pattern asosida qurilgan.

## 🎯 Xususiyatlar

- 📱 Turli IR qurilmalarni boshqarish (TV, AC, Projector va boshqalar)
- 🔌 Bluetooth orqali tashqi IR modulga ulanish
- 💾 Room Database bilan ma'lumotlarni saqlash
- 🎨 Material Design 3 bilan zamonaviy UI
- ⚡ Jetpack Compose asosida yaratilgan
- 🏗️ Clean Architecture pattern
- 💉 Hilt Dependency Injection

## 🏛️ Arxitektura

Proyekt Clean Architecture prinsiplariga amal qiladi va quyidagi modullarga bo'lingan:

```
IRControl2/
├── app/                    # Presentation layer (UI, ViewModels)
├── domain/                 # Business logic (Use cases)
├── data/                   # Data layer (Repositories, Database)
├── core/                   # Shared models and utilities
└── ui/                     # Reusable UI components
```

### Modullar:

- **app**: Presentation layer - Activity, Screens, ViewModels, Navigation
- **domain**: Domain layer - Use Cases, Business logic
- **data**: Data layer - Room Database, DAOs, Repositories
- **core**: Core models - Domain models, Enums
- **ui**: UI Components - Reusable Compose components

## 🛠️ Texnologiyalar

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: Clean Architecture, MVVM
- **Database**: Room
- **DI**: Hilt
- **Async**: Coroutines & Flow
- **Navigation**: Jetpack Navigation Compose

## 📋 Talablar

- Android Studio Hedgehog | 2023.1.1 yoki yangi versiya
- JDK 17
- Android SDK 34
- Minimum Android 7.0 (API 24)

## 🚀 O'rnatish

1. Repositoriyani klonlash:
```bash
git clone https://github.com/rebelclub/IRControl2.git
cd IRControl2
```

2. Android Studio'da loyihani ochish

3. Gradle sync tugashini kuting

4. Ilovani ishga tushurish (Run)