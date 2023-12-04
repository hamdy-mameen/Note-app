# Note-app
a Note app in kotlin that apply clean architecture design

# Description
Note app is a clean architecture app, it has three layers domain, data and app layers. 
from inner most layer to outer one, the inner is the most abstract one "domain".
domain contains models,usecases and repository interface for dependency inversion with data layer
that implement this interface.
the outer most layer is app layer, only this layer depends on android sdk 
## Technologies
* Clean architecture
* Modularization
* MVVM
* Coroutine
* Kotlin flow
* Hilt & dagger
* Viewbinding
* Room
* Navigation component
* xml
## Screenshots
  <img src="https://github.com/hamdy-mameen/Note-app/assets/54679951/71e32c80-2ce2-4d61-a23e-8a5aa4da4c28" width="200" />
  
  <img src="https://github.com/hamdy-mameen/Note-app/assets/54679951/3848ffeb-1c25-46ff-96f2-544740f2be40" width="200" />
  
  <img src="https://github.com/hamdy-mameen/Note-app/assets/54679951/5625fb78-cb21-4efa-8df0-4c5c21e3fa75" width="200" />
 
# How to install the project
Clone or download zip file then open it using the latest version of android studio
