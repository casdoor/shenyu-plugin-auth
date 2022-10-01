---
title: ShenYu
description: Using Casdoor in ShenYu
keywords: [ShenYu]
---

ShenYu has casdoor plugin to use casdoor

## Step1. Deploy Casdoor

Firstly, the Casdoor should be deployed. 

You can refer to the Casdoor official documentation for the [Server Installation](/docs/basic/server-installation).

After a successful deployment, you need to ensure:

- The Casdoor server is successfully running on **http://localhost:8000**.
- Open your favorite browser and visit **http://localhost:7001**, you will see the login page of Casdoor.
- Input `admin` and `123` to test login functionality is working fine.

Then you can quickly implement a casdoor based login page in your own app with the following steps.

## Step2. Configure Casdoor application
#### 1. Create or use an existing Casdoor application.
#### 2. Add Your redirect url 
   ![Casdoor Application Setting](static/img/shenyu_config.png)
#### 3. On the certificate editing page, you can see your `Certificate`.
   ![Casdoor Certification Setting](static/img/shenyu_cert.png)

## Step3. Use casdoor plugin in shenyu
### 1. Config casdoor plugin in shenyu

   ![Shenyu Config Plugin](static/img/shenyu_configPlugin.png)

note: because the shenyu only have Single line input box so we need add \n in every line of cert.
   ![Casdoor Certification Setting](static/img/shenyu_cert2.png)

You can copy it and paste it on the certificate of shenyu casdoor config.

**You don't need save it in casdoor certificate editing page**,because it just for copying.

### 2. Confing shenyu casdoor's plugin
   ![Shenyu Casdoor](static/img/shenyu_casdoor.png)
   You can config what you need to use casdoor config

### 3. Get the service and use 

   #### 3.1 Visit the Web directly like
   ![Shenyu Fail Login](static/img/shenyu_faillogin.png)

   #### 3.2 Use casdoor login like this
   ![Shenyu Login](static/img/shenyu_login.png)
   ![Shenyu Success Login](static/img/shenyu_successlogin.png)

   #### 3.3 Carry token in Headers,you also can visit it
   ![Shenyu token](static/img/shenyu_token.png)

   #### 3.4 It also can save name,id and organization in Headers so that you can use them in next time  

