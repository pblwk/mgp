# Deployment Instructions

Above all, please use the [Rspec](config/Rspec) to reserve a resource in GENI. Please make sure that the Publicly Routable IP option is checked. 

## Step 1. Deploy the Recognition System

Log in one of the nodes you create. Suppose the public IP is `111.111.111.111`

### Step 1.1. Install Java

Install SDKMan: 

```bash
curl -s "https://get.sdkman.io" | bash
```

Follow SDKMan's instructions, run the command like below. Please replace `phuang` with your username.

```bash
source "/users/phuang/.sdkman/bin/sdkman-init.sh"
```

Install Java:

```bash
sdk install java
```
Verify you've installed it successfully:

```bash
java -version
```

### Step 1.2. Install Nginx

Install Nginx:

```bash
sudo apt update
sudo apt install nginx
```

Verify you've installed it successfully: 

```bash
nginx -v
```

### Step 1.3 Start Recognition System and Nginx

Download the .jar file to your home directory. 

```bash
cd ~
wget https://github.com/pblwk/mgp/releases/download/jar/mini-geni-0.0.2-SNAPSHOT.jar
```

If the download fails, you can visit https://github.com/pblwk/mgp/releases/ to download it. 

Boot the recognition system:

```bash
nohup java -Xmx1G -jar mini-geni-0.0.2-SNAPSHOT.jar &
```

Several minutes later, verify that you've booted it successfully. It will show "Greetings from  ImageController". 

```bash
curl localhost:8080/hello
```

Create the Nginx config file `server.conf` save it to `/etc/nginx/conf.d/` . Please make sure that `111.111.111.111`  should be replaced with your node's IP address.

```nginx
server {
        listen 80;
        listen [::]:80;
        server_name 111.111.111.111;
        client_max_body_size 100M;


        location / {
             proxy_pass http://127.0.0.1:8080/;
             proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
             proxy_set_header X-Forwarded-Proto $scheme;
             proxy_set_header X-Forwarded-Port $server_port;
        }

}
```

Check the Nginx configuration file:

```bash
sudo nginx -t
```

Start nginx

```bash
sudo systemctl restart nginx.service
```

On your local browser, visit `111.111.111.111/hello`, you should see the greeting prompt. 

## Step 2. Deploy the Web Interface

Log in another node you create. Suppose the public IP is `222.222.222.222`

Download the web interface:

```bash
cd ~
git clone https://github.com/pblwk/mgp.git
```

Modify the server address. Use vim open the `mgp/client/index.html` file, change the `SERVER_ADDRESS`  to correct IP address of the recognition system, just like: 

```javascript
const SERVER_ADDRESS="http://111.111.111.111"
```

Install Nginx:

```bash
sudo apt update
sudo apt install nginx
```

Verify you've installed it successfully: 

```bash
nginx -v
```

Create an Nginx config file `client.conf` save it to `/etc/nginx/conf.d/` . Please make sure that `222.222.222.222`  should be replaced with your node's IP address. `phuang` should be replaced with your own username. 

```nginx
server {
        listen 80;
        listen [::]:80;
        server_name 222.222.222.222;
        root /users/phuang/mgp/client;

        location / {
	}

}
```



Check the Nginx configuration file:

```bash
sudo nginx -t
```

Start nginx

```bash
sudo systemctl restart nginx.service
```

On your local browser, visit `222.222.222.222`, you should see the webpage and upload the image. 

Please don't upload the image that exceeds 200kB size. 

## Step 3. Setup and Reproduce the experiment.

The script of the experiment is in [experiment.py](experiment/experiment.py). You should run the script on your own machine.

Firstly, you should run replace the `URL` variable with correct recognition system IP address, just like:

```Python
  URL = "http://111.111.111.111/upload"
```

Secondly, you should determine the number of threads by modify the `THREAD_NUM` variable. Please do not set it to exceed 10.

Run the script:

```bash
python experiment.py
```

What's more, you can limit the capacity of the network (e.g., limit to 10kbit/s) by running the the command below on recognition system's node. 

```bash
sudo tc qdisc replace dev eth0 root netem rate 10kbit 
```

And reset it. 

```bash
sudo tc qdisc del dev eth0 root
```









