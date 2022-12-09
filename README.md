Deployment Instructions

Above all, please use the [Rspec](config/Rspec) to reserve a resource in GENI. Please make sure that the Publicly Routable IP option is checked. 

Step 1. Deploy the Recognition System

Log in one of the nodes you create. Suppose the public IP is 

Step 1.1. Install Java

Execute

```bash
curl -s "https://get.sdkman.io" | bash
```

Follow SDKMan's instructions, run the command like below. Please replace `phuang` with your username.

```bash
source "/users/phuang/.sdkman/bin/sdkman-init.sh"
```

Run 

```bash
sdk install java

```

to install Java.

Run

```bash
java -version
```

to verify you've installed it successfully.

Step 1.2. Install Nginx

```bash
sudo apt update
sudo apt install nginx
```















Step 1.2 Install Nginx





Step 1.3







Step 2. Deploy the Web Interface













