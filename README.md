# Android Architectural Pattern Template

A sample app for building android applications based on MVVM using RxJava with minimum and elemental dependencies

![Repo](./assets/CleanArch.png?raw=true "Architecture")

- Hilt for Dependency Injection
- Domain layer implemented with DTO to Model mapper
- Resource state
- Android Jetpack Room for ORM
  - [ ] Composite objects persistence
  - [x] Type converters (Date)
- Unit tests:
  - View Model
  - [ ] Repositories
  - [ ] Interactors
- Image rendering with Glide
- Navigation
### TODO
- [ ] Migrate to coroutines
- [ ] Pagination
- [x] Base ViewModel, Base Fragment, Base Activity
- [ ] Move domain classes to Kotlin module