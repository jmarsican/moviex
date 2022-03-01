# Moviex

Implementation of a coding challenge

![Repo](./assets/repository_pattern.png?raw=true "Architecture")

1. Data layer is implemented by a Repository pattern, which provides required data as a black box, no matter where that data comes from (network, DB, etc)
Classes: DataRepositoryImpl, RepoDatabase, MoviesService.
2. Presenter layer is the orchestrator of major logic mediating view and data layer. It represents the functionality or flow within the MVP triad. The presenter coordinates the UI with the data, ensuring they are in sync.
Classes: BaseMVPPresenter, MVPPresenter and business specific ones
3. The UI layer has thins view components with methods that consist of little more than firing properly typed events to be handled by the presenter. Also Android framework classes belong to here
Classes: BaseMVPActivity, BaseMVPFragment, MVPView.

![Repo](./assets/MVP.png?raw=true "Architecture")

- BaseMVPActivity: Base class that will contain all logic shared by all activities and has type safety control as well
- BaseMVPFragment: Base class that will contain all logic shared by all fragments and has type safety control as well
- BaseMVPPresenter: Base class for all presenters with type safety
- RepoDatabase: Room database implementation
- DataRepositoryImpl: Repository of data, it tries to fetch up to date data from network. If no updated data could be fetched it delivers data from database.
- ItemsFragment: The UI implementation
- ItemsPresenterImpl: Business specific presenter implementation for a specific screen

1. Single Responsibility is the first principle of the five stated by SOLID acronyms for good object oriented code quality. It's basically make sure any class has one and only one high level responsibility and not many mixed causing tightly coupled code and poor cohesion.
2. Clean code aims to achieve a high reusable, scalable and testeable code with several components modeled by abstraction layers. Each layer knows only the immediate layer components. For example Use Case layers are platform agnostic so they have no dependency on frameworks and are very testeable (example: pure Java, no Android deps)

## Challenge:
Consumir el API de películas y series de la siguiente pagina: https://developers.themoviedb.org/4/getting-started/authorization
- Debe tener tres categorías de películas y/o series: Popular, Top Rated, Upcoming.
- Cada película y/o series debe poder visualizar su detalle.
- Debe funcionar tanto online como offline (cache).
- Debe tener un buscador offline por categorías.
  Valoraciones extras:
1. Visualización de los videos en el detalle de cada ítem.
2. Transiciones, Animaciones, UI/UX.
3. Buscador Online.
4. Pruebas Unitarias
   Una vez acabada la prueba describa en un readme brevemente:
1. Las capas de la aplicación (por ejemplo capa de persistencia, vistas, red, negocio, etc) y qué clases pertenecen a cual.
2. La responsabilidad de cada clase creada.
   Responda y escriba dentro del Readme con las siguientes preguntas:
1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?
2. Qué características tiene, según su opinión, un “buen” código o código limpio?
   Entregables:
1. Archivo APK.
2. Link de github del código de la prueba
3. Dentro de repositorio de github el readme con las explicaciones y preguntas solicitadas.
   Deadline:
   Tres días o 72 horas después de la confirmación de recibido del email. 
