# insolabs_meetup_serverless #
Código fonte do [meetup](https://www.meetup.com/pt-BR/Insolabs/events/261136589/) apresentado dia 08/05/2019 por ***Milton Bertachini*** entre 19:00 e 20:00 no espaço oferecido pela [Trigg](trigg.com.br) a apresentação pode ser encontrada [aqui](https://docs.google.com/presentation/d/e/2PACX-1vQHyV4on3yMi5MMou9t3tP6CV9E51zqS6P42Dn0PYlh_0Dn0kqxS0GEmcycjW2Jo-0DxleNTBLALID4/pub?start=false&loop=false&delayms=3000).

## O que foi utilizado neste projeto? 
- [AWS Cloudformation](#criando-a-stack-cloudformation)
- AWS SNS 
- AWS SQS
- AWS Lambda 
- AWS ApiGateway
- Java 8 
- Gradle 4.6 

### Gerando o Artefato ###
```shell
gradle build
```

### Configurando o Eclipse ###
Necessário instalar o [lombok.jar](https://projectlombok.org) na IDE e utilizar o comando:
```shell
gradle eclipse
```

### Criando a stack cloudformation ###
Toda definição da infraestrutura [AWS](https://aws.amazon.com/pt/) necessária para rodar essa demonstração está dentro de um arquivo [SAM Template](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-reference.html) que pode ser encontrado [aqui](template.yml).

Para montar essa estrutura você deve executar o comando:
```shell
aws cloudformation package --template-file template.yml --s3-bucket [[SEU_BUCKET]] --output-template-file outTemplate.yml
```
Isso vai gerar o artefato *outTemplate.yml* que deve ser importado dentro do [AWS CloudFormation](https://aws.amazon.com/pt/cloudformation/) como um change-set de uma stack.
