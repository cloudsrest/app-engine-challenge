## Getting Started

#### First step: install dependencies:
```shell
npm install
```
```shell
npm install -g gulp
```

#### Run the app in your browser in development mode:
```shell
npm run start-dev
```
This will start the ```gulp watch``` process which will automatically build the assets anytime a file change is detected.
In addition, it will run a local proxy server on port 8081 that proxies requests to the remote prod server hosting the
API.

If you wish to specify a different port and/or remote host, you can do so by passing options to the node process
```shell
node server.js --port 9000 --remote http://localhost:8080
```

This is useful if you are running the API locally and just want to run the UI against your local setup.

## Building
```shell
gulp build
```
This command will go through the process of transpiling all of the application typescript, sass, and html then bundle
all of the necessary files together and place them in the ```build``` directory.
The files needed to run the application are:
```
- index.html
- build
    - js
        - app.bundle.js
        - es6.shim.min.js
        - Reflect.js
        - zone.js
    - css
        - *
    - fonts
        - *
    - pages
        - *
```

## Testing

#### To run test suite and enable auto-watch:
```shell
npm run test:dev
```

#### To run test suite in single-run mode:
```shell
npm test
```
