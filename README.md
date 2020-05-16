# my.university Android Client
This is an attempt to create usable android client for my.university.innopolis.ru website.

* Course - Mobile Development
* Product owner - Aleksandr Simonenko (Instructor)

## Team
* Bulat Khabirov (Team lead, project backbone)
* Dilshat Salikhov (Profile page, Authentication activity)
* Sergey Malyutkin (Task manager, HTTP services))
* Valeria Ahmetzhanova (Other fragments)

## Tech stack
* Target platform - Android 6
* Language - Kotlin 1.3
* Testing - JUnit, Espresso
* Build - Gradle
* Code smell - ktlint, detekt
* Libraries - Glide, Dagger, RxAndroid, Jsoup, Material, Navbar

## Tools
* IDE - Android Studio
* Task management - [Trello](https://trello.com/b/pb7LdlRR/md-university-project)
* VCS - git hosted on GitHub
* CI/CD - GitHub Actions
* Communications - telegram and periodical meetings

## Process
* Methodology - [Kanban](https://trello.com/templates/engineering/kanban-template-LGHXvZNL)
* Prioritization - [trello system for kanban](https://blog.trello.com/kanban-data-nave)
* Task format - Feature/Requirement description
* Dev. process - simplified GitHub Flow
* Sprint size - 2 weeks

## Branching policy
* All branches should be created from the **master** (stable branch)
* All pull-requests should be made into the **develop** (unstable branch)
* When **develop** is stable and has enough features for a release we need to create a pull-request **develop** -> **master**

## Naming policy
* Branches - by the code of the task and short description, e.g. *MD-10-sso-auth*
* Commits - by code of the branch and message with the work done, e.g. *MD-10 | added basic auth procedure*

## Specific traits
* No staging server
* Result oriented development
* No experience in Android
* Used Kotlin-Android [template](https://github.com/cortinico/kotlin-android-template)

## Evaluation
![Sprint Progress](https://docs.google.com/spreadsheets/d/e/2PACX-1vSHDLY6gFINy8nBgLJb81mLj9IkczivmAyML4zdw_dxAN6vNRxIOuSpkBkKgUA4ixQG_P8MFCwEXwbY/pubchart?oid=815173095&amp;format=image)
