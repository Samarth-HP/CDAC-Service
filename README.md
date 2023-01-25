<p align="center">
  <h1>CDAC-Service</h1>
</p>

[![Build](https://github.com/Samagra-Development/CDAC-Service/actions/workflows/build.yml/badge.svg)](https://github.com/Samagra-Development/CDAC-Service/actions/workflows/build.yml)
[![Docker Build](https://github.com/Samagra-Development/CDAC-Service/actions/workflows/docker.yml/badge.svg)](https://github.com/Samagra-Development/CDAC-Service/actions/workflows/docker.yml)
[![Dockerhub Push](https://github.com/Samagra-Development/CDAC-Service/actions/workflows/docker-push.yml/badge.svg)](https://github.com/Samagra-Development/CDAC-Service/actions/workflows/docker-push.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Samagra-Development_CDAC-Service&metric=alert_status)](https://sonarcloud.io/dashboard?id=Samagra-Development_CDAC-Service)

## About :open_book:

A Spring-Boot based microservice providing REST endpoints for using SMS services provided by CDAC ```(Centre for Development of Advanced Computing)``` i.e send single/bulk messages.

## Features :dart:

- [x] Send single unicode message
- [x] Send bulk messages
- [x] Send single message
- [x] Send single unicode message
- [x] Send otp messages
- [x] Send messages from CSV
- [ ] Add audit logs

## Requirements :scroll:

1. Your machine should have [Python](https://www.python.org/downloads/) and ```pip``` installed.

*Note: Preferable Npm version (3.9.7) and Ubuntu OS version(18.04)**

2. Check the python and ubuntu version by running following commands.
```sh
python --version
lab_release -a
```


## Installation Steps :walking:

### 1. Fork it :fork_and_knife:

You can get your own fork/copy of [CDAC-SERVICE](https://github.com/Samagra-Development/CDAC-Service.git) by using the <kbd><b>Fork</b></kbd> button.

### 2. Clone it :busts_in_silhouette:

You need to clone (download) it to a local machine using

```sh
git clone https://github.com/Samagra-Development/CDAC-Service.git
```

> This makes a local copy of the repository in your machine.

Once you have cloned the `CDAC-SERVICE` repository in GitHub, move to that folder first using the change directory command.

```sh
cd CDAC-SERVICE
```

Move to this folder for all other commands.

### 3. Set it up :arrow_up:

First, You need to add some hosts to your `/etc/hosts` file as mentioned [here](https://mgov.gov.in/PushSMS_Q1) on `mgov.gov.in` website.

_Note: These ip addresses could subject to change so please make sure you are adding the correct ip addresses. You can use ping utility to identify the ip of a host._

```
164.100.129.128 mgov.gov.in
164.100.129.141 msdgweb.mgov.gov.in
```

Run the following commands to see that _your local copy_ has a reference to _your forked remote repository_ in GitHub :octocat:

```sh
git remote -v
origin  https://github.com/Your_Username/CDAC-Service.git (fetch)
origin  https://github.com/Your_Username/CDAC-Service.git (push)
```
### 4. Run it :checkered_flag:

- rename `env.sample` file to `.env` and set the correct variable values
- build and run the container `docker-compose up -d`
```sh
    docker-compose up -d
```

### 5. API
Start the server and go to ```http://localhost:8080/api/swagger-ui.html```
