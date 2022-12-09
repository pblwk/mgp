import requests
import time
import threading
import statistics
# url = "http://localhost:8080/upload"

results=[[] for _ in range(5)]

def experiment():
  REPEAT_TIMES=15
  IMAGE_NAME="n01443537_goldfish"
  IMAGE_EXT=".jpeg"
  IMAGE_DIR="."
  URL = "http://143.215.216.207/upload"
  # URL = "http://localhost:8080/upload"

  for index in range(0,5):

    full_image_name=IMAGE_NAME+"_"+str(index)+IMAGE_EXT
    full_image_path=IMAGE_DIR+"/"+full_image_name

    sum_duration=0
    for counter in range(REPEAT_TIMES):
      payload={}
      files=[
        ('file',(full_image_name,open(full_image_path,'rb'),'application/octet-stream'))
      ]
      headers = {}
      start_time=time.time()
      response = requests.request("PUT", URL, headers=headers, data=payload, files=files)
      duration=time.time()-start_time
      sum_duration+=duration
      print(response.text)
    print(index, "mean rtt", sum_duration / REPEAT_TIMES)
    results[index].append(sum_duration/REPEAT_TIMES)



if __name__=="__main__":
  THREAD_NUM=1
  threads=[]
  for thread_index in range(THREAD_NUM):
    t = threading.Thread(target=experiment)
    t.start()
    threads.append(t)

  for t in threads:
    t.join()

  for result in results:
    print(statistics.mean(result))
