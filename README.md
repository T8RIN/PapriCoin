<h1 align="center">PapriCoin</h1>

<p align="center">
  <img alt="API" src="https://img.shields.io/badge/Api%2021+-50f270?logo=android&logoColor=black&style=for-the-badge"/></a>
  <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-a503fc?logo=kotlin&logoColor=white&style=for-the-badge"/></a>
  <img alt="Compose" src="https://img.shields.io/static/v1?style=for-the-badge&message=Jetpack+Compose&color=4285F4&logo=Jetpack+Compose&logoColor=FFFFFF&label="/></a> 
  <a href="https://github.com/t8rin"><img alt="Profile" src="https://img.shields.io/badge/Github-T8RIN-002200?logo=github&logoColor=white&style=for-the-badge"/></a> 
</p>

<p align="center">  
PapriCoin demonstrates Jetpack Compose usage to build modern app based on Clean Architecture and newest Tech-Stack.</br>Repository also has local and remote data source, if you go offline app will switch on saved cache to work independently.
</p>
</br>

![image](https://user-images.githubusercontent.com/52178347/161929672-890dedca-086a-49a4-8fa2-53ef8932a855.png)

## Download
Go to the [Releases](https://github.com/t8rin/PapriCoin/releases) to download the latest APK.


## Tech stack & Open-source libraries
- Minimum SDK level 21

- [Kotlin](https://kotlinlang.org/) based 

- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous work

- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) to emit values from database directly to compose state.

- [Accompanist](https://github.com/google/accompanist) to expand jetpack compose opportunities.

- [Hilt](https://dagger.dev/hilt/) for dependency injection.

- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST API using abstractions.

- [Moshi](https://github.com/square/moshi/) - A modern JSON library to serialization/deserialization.

- [Coil](https://github.com/coil-kt/coil) - loading images.

- Jetpack
  - Lifecycle - Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel - Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - Compose - Modern Declarative UI style framework based on composable functions.
  - Room Persistance - Construct Database using abstract layer.
  
- Architecture
  - Clean Architecture (UI - ViewModel - Use Cases - Repository)
  - Repository Pattern
  - MVVVM

# License
```xml
Designed and developed by 2022 T8RIN

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
