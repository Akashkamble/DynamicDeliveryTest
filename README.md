# DynamicDeliveryTest
Testing of On-Demand modules with bundletool.


For initial process follow [google codelab](https://codelabs.developers.google.com/codelabs/on-demand-dynamic-delivery/index.html#0).

## Testing On-Demand modules locally.

1. Use **FakeSplitInstallManagerFactory** instead of **SplitInstallManagerFactory**.
2. Download [BundleTool.jar](https://github.com/google/bundletool/releases)
3. Create bundletool alias with below command
    ```
    alias bundletool="java -jar ~/Downloads/bundletool-all-1.2.0.jar"
    ```
4. Build app bundle from android studio > Build > Build Bundle.
5.Create APKs from app bundle with below command
    ```
    bundletool  build-apks --local-testing --overwrite --bundle=app/build/outputs/bundle/bebug/app-debug.aab output=temp/APKS/app.apks
    ```
    
    This will create temp folder in your Project folder. You can change the destination of app.apks file.
    
6. Install .apks file in your device with below command
    ```
    bundletool install-apks --apks=temp/APKS/app.apks
    ```
    
    Here value of --apks flag should be equal to value of output in step 5.
    
7. Now open an app from app drawer and test on-demand module feature locally.    
