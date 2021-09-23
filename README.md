# net-batch
[![ci-test](https://github.com/ningenMe/net-batch/actions/workflows/ci-test.yml/badge.svg)](https://github.com/ningenMe/net-batch/actions/workflows/ci-test.yml)
[![cd-resource-push](https://github.com/ningenMe/net-batch/actions/workflows/cd-resource-push.yml/badge.svg)](https://github.com/ningenMe/net-batch/actions/workflows/cd-resource-push.yml)

趣味開発のバッチ処理  

```bash
gradle clean build

//blogJob
java -Dspring.batch.job.names=blogJob -jar build/libs/batch-0.0.1-SNAPSHOT.jar param=tmp
//atcoderUserJob
java -Dspring.batch.job.names=atcoderUserJob -jar build/libs/batch-0.0.1-SNAPSHOT.jar param=tmp
```

## document
TODO 

## architecture
[system diagrms](https://ningenme.net/systems)

|            |      architecuture             |  
|----------- |------------------------------- |  
|hosting     | ecs                            |  
|ci/cd       | github actions, aws codedeploy |  
|application | Spring Batch                   |  

## history

|version  |repository                                                           |tech stack|  
|------   |---------                                                            |----------- |  
|v3 (this)|[net-batch](https://github.com/ningenMe/net-batch)                   |spring batch + ecs|  
|v2       |[ningenme-net-batch](https://github.com/ningenMe/ningenme-net-batch) |spring boot + ec2|  
|v1       |[ningenme.net-batch](https://github.com/ningenMe/ningenme.net-batch) |python + ec2|  
