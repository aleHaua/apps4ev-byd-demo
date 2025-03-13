# apps4ev-byd-demo

- autor: Alessandre Haua
- contato: https://www.linkedin.com/in/ahaua/

## Introdução
Importante mencionar que o código fonte aqui disponibilizado faz parte de um **estudo inicial** para desenvolvimento de aplicativos específicos para carros eletrificados. Entenda-se como **estudo inicial**, um processo ordenado de aprendizado, baseado em experimentos de desenvolvimento e apoiado unicamente pela documentação do fabricante, disponibilizada atualmente apenas em **chinês simplificado**.

A necessidade desse estudo é relativa a pouca quantidade de material encontrado sobre o assunto na Internet, seja em português ou qualquer outro idioma. Embora a fabricante celebre 30 anos de mercado, a decição de abertura da plataforma e disponibilização do SDK é recente.

Vale lembrar que tais estudos e experimentos devem ser conduzidos com cuidado, por um profissional ou desenvolvedor experiente, seguindo todas as etapas do ciclo de desenvolvimento para este tipo de produto. Tais etapas compreendem minimamente: estudo, planejamento, desenvolvimento, testes unitários, refinamento, implantação e teste final. Recomendo que a etapa de refinamento seja um retorno à todas as etapas anteriores, sendo executada de forma recorrente, multiplas vezes, até que o resultado dos testes unitários sejam satisfatórios. Além disso, recomendamos que os testes unitários sejam conduzimos em ambiente simulado, utilizando o próprio software simulador disponibilizado pela fabricante, antes da implantação do aplicativo compilado no veículo para testes finais.

Devemos seguir rigorosamente todas as instruções e recomendações fornecidas pelo fabricante, além de mantermos todos os cuidados já citados sempre preservados. Lembre-se que temos como plataforma de destino final para o nosso desenvolvimento, um patrimônio de centenas de milhares de reais.

> 
> ### Aviso
> Tão importante quanto, é sabermos que o código aqui disponibilizado, bem como o conjunto de instruções aqui documentadas, só deve ser implementado, compilado e implantado em seu veículo, e por sua única e própria responsabilidade. Ao decidir continuar, você assume todos e quaisquer riscos que invalidem a garantia do veículo ou danifiquem o software do mesmo, comprometendo operações ou, até mesmo, inutilizando parcialmente ou totalmente o equipamento.
> 

## Configuração do Ambiente

A configuração inicial do ambiente de desenvolvimento para o projeto depende de basicamente os seguintes pontos:

> * Download e configuração do SDK do fabricante
> * Download e configuração do JAVA
> * Download e configuração do Android Studio

### 1. Download do SDK do Fabricante

#### Procedimento:

1. Acesse a [página principal](https://oip.byd.com/addons/cms/document/index?document_type_id=26) no site global, onde a fabricante disponibiliza os recursos para uso do SDK (recomendo usar uma ferramenta de tradução online);

    ![BYD SDK DOWNLOAD](/DOC/assets/development-setup-01.png)

2. Baixe o SDK, o simulador e a documentação de desenvolvimento disponibilizados pela fabricante. Ao final, você deverá ter os seguintes arquivos em sua pasta de download:

  * SDK: sdk_v1.0.5.tar.gz
  * Simulador: aff821e394287088787bb87f85c72bc1.zip
  * Documentação: a00e8d77e99c59cac9e1d8368c871705.pdf

    > ### Nota
    > Os nomes dos arquivos são referentes às versões 1.0.5 e compatibilidade do sistema 7.1, podendo ter seus outros nomes em versões posteriores. Aqui o mais importante é baixar cada um dos recursos em sua pasta de preferência.


3. Usando um utilitário ZIP, descompacte o arquivo do SDK em uma pasta do seu sistema de arquivos. Como referência, utilizei o caminho: ``C:\Lab\BYD\SDK``

    ![BYD SDK UNZIPED](/DOC/assets/development-setup-02.png)

4. Depois de descompactar o arquivo tar.gz do SDK, localize o arquivo **BYD_AUTO_SDK_v1.0.5.ZIP** em **C:\Lab\BYD\SDK\SDK_v1.0.5** e descompacte o seu conteúdo em ``C:\Lab\BYD\SDK``

5. Usando um utilitário ZIP, descompacte o arquivo do Simulador em uma pasta do seu sistema de arquivos. Como referência, utilizei o caminho: ``C:\Lab\BYD\Simulator``

    ![BYD Simulator UNZIPED](/DOC/assets/development-setup-03.png)

### 2. Download e configuração do JAVA

A partir desse ponto, eu utilizei a documentação encontrada em:

  ![PDF SDK](/DOC/assets/development-setup-04.png)

O procedimento abaixo visa facilitar a configuração e funcionou perfeitamente com a versão 1.0.5 do SDK. Caso você encontre uma versão mais atualizada e este procedimento não funcione para ela, siga os passos do PDF encontrado neste mesmo SDK.

#### Procedimento:

1. Acesse o [site de download](https://www.oracle.com/br/java/technologies/downloads/archive/) da Oracle para baixar o JDK do JAVA;

2. Em **Previous JAVA releases**, **JAVA SE**, clique no link **Java SE 8 (8u202 and earlier)**. Na página que abrir, localize a versão **Java SE Development Kit 8u102** e clique na versão de JDK apropriada para o seu sistema operacional:

    ![PDF SDK](/DOC/assets/development-setup-05.png)

  > ### Nota
  > Eu procurei utilizar especificamente essa versão porque é a mesma indicada na documentação do SDK conforme aparece nas telas do PDF. Embora, no mesmo documento, há o [link para a versão 431](https://www.oracle.com/java/technologies/downloads/#java8) que é o último release da versão JAVA 8. Acredito que ambas as versões irão funcionar. Só não recomendo utilizar outra versão diferente do JAVA 8;

3. Instale o JAVA JDK em sua pasta de preferência. Por padrão, a instalação é feita na pasta ``C:\Program Files\Java\jdk1.8.0_102``. Irei utilizar esta pasta como referência para a configuração que faremos a seguir;

4. Abra as informações do sistema para configurar o path do sistema operacional. Clique com o botão direito no botão do Windows e escolha **Sistema**:

    ![PDF SDK](/DOC/assets/development-setup-06.png)

5. Depois localize a opção **Configurações avançadas do sistema** e clique para a próxima etapa:

    ![PDF SDK](/DOC/assets/development-setup-07.png)

6. No diálogo **Propriedades do Sistema**, clique no botão **Variáveis de Ambiente**;

    ![PDF SDK](/DOC/assets/development-setup-08.png)

7. Em **Variáveis do sistema**, localize a variável **Path** e clique no botão **Editar**;

    ![PDF SDK](/DOC/assets/development-setup-09.png)

8. Clique no botão **Novo** duas vezes para incluir duas novas entradas, conforme abaixo:

  * %JAVA_HOME%\bin;
  * %JAVA_HOME%\jre\bin;

    ![PDF SDK](/DOC/assets/development-setup-10.png)

9. Clique no botão **OK** para retornar para o diálogo **Variáveis de Ambiente**;

10. No diálogo **Variáveis de Ambiente**, clique no botão novo para criar a variável de ambiente **JAVA_HOME** apontando para a pasta onde instalou o JDK do JAVA 8;

    ![PDF SDK](/DOC/assets/development-setup-11.png)

11. No diálogo **Variáveis de Ambiente**, clique no botão novo para criar a variável de ambiente **CLASSPATH** apontando para duas bibliotecas importantes a serem utilizadas pelo JDK: ``%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar``

    ![PDF SDK](/DOC/assets/development-setup-12.png)

12. Clique em **OK** no diálogo **Variáveis de Ambiente** e depois em **OK** no diálogo **Propriedades do Sistema**;

13. Abra uma nova tela de **Terminal do Windows** e digite o comando ``java -version``. O comando deverá retornar a versão do JDK instalada e configurada no path do sistema:

    ![PDF SDK](/DOC/assets/development-setup-13.png)

  >
  > ### Atenção
  > Caso a versão apresentada não esteja correta, verifique se todas as variáveis de ambiente foram criadas e apontam para os caminhos corretos. Verifique também se não há mais de uma definição para as variáveis. 


### 3. Download e configuração do Android Studio

#### Procedimento:

1. Acesse o [site de download](https://developer.android.com/studio) do Android Studio e baixe a versão apropriada para o seu sistema operacional;

    ![Android Studio download](/DOC/assets/development-setup-14.png)

2. Instale o Android Studio seguindo as configurações padrões;

3. Após a conclusão da instalação, vamos configurar a pasta para o SDK. Clique no botão **More Actions**, no centro da tela, e escolha a opção **SDK Manager**:

    ![Android Studio download](/DOC/assets/development-setup-15.png)

4. Na tela do SDK Manager, localize o campo Android SDK Location e selecione o local onde o SDK da fabricante foi descompactado ``C:\Lab\BYD\SDK\byd-auto_sdk_windows``:

    ![Android Studio download](/DOC/assets/development-setup-16.png)

5. Clique em **OK** para salvar as configurações;

6. Após definir o caminho para o SDK a ser utilizado, vamos configurar o emulador Android. Clique no botão **More Actions** e escolha **Virtual Device Manager**:

    ![Android Studio download](/DOC/assets/development-setup-17.png)

7. Na tela **Device Manager**, clique no botão **+** para criar um novo dispositivo virtual:

    ![Android Studio download](/DOC/assets/development-setup-18.png)

8. Na tela **Configure Hardware Profile**, insira as informações abaixo:

  * Device Name: BYD
  * Device Type: Phone/Tablet
  * Screen size: 7,8
  * Resolution: 1080 x 1920 px
  * RAM: 2048 
  
9. Deixe as demais informações conforme original e clique em **Finish**;

10. De volta na tela Select Hardware, mantenha o dispositivo recém configurado como selecionado e click em **Next**;

    ![Android Studio download](/DOC/assets/development-setup-19.png)

11. Na etapa **System Image**, selecione **x86 Images** e escolha a versão de Android Nougat, API level 25, Android 7.1.1 e system imagem x86_64, exatamente como na imagem abaixo:

    ![Android Studio download](/DOC/assets/development-setup-20.png)

12. Clique no botão de download ![Android Studio download](/DOC/assets/development-setup-21.png) ao lado do nome da imagem para iniciar o download dos arquivos:

    ![Android Studio download](/DOC/assets/development-setup-22.png)

13. Assim que o download da imagem for concluído, clique no botão **Finish**:

    ![Android Studio download](/DOC/assets/development-setup-23.png)

14. De volta a tela **System Image**, mantenha a imagem recentemente baixada como selecionada e clique em **Next**:

15. Na tela **Android Virtual Device**, defina o nome do dispositivo e a orientação padrão como paisagem, conforme abaixo:

    ![Android Studio download](/DOC/assets/development-setup-24.png)

16. Depois clique no botão **Finish**.

  > 
  > ### Nota
  > Caso durante a geração do dispositivo apareça uma mensagem informando **Intel Virtualization Technology (vt, vt-x) is not enabled**, reinicie o computador e habilite as configurações de tecnologia de virtualização da Intel na BIOS. Acesse em  em **Advanced Mode > Advanced > CPU > Configuration > Intel Virtual Technology > Enabled**
  >

### 4. Execução do Emulador

Utilize essa etapa como uma etapa de verificação da configuração até aqui. Se após a execução dessa etapa não for possível carregar o emulador conforme configurado, provavelmente algum passo do procedimento não foi executado, ou não foi executado de forma correta. Reveja todos os passos com cuidado e atenção.

#### Procedimento:

1. Abra o Android Studio, clique no link **More Actions** no centro da janela e escolha a opção Virtual Device Manager:

    ![AVD verification](/DOC/assets/development-setup-25.png)

2. Selecione o dispositivo BYD Auto na lista de dispositivos e clique no botão ![AVD verification](/DOC/assets/development-setup-27.png):

    ![AVD verification](/DOC/assets/development-setup-26.png)

3. Aguarde a inicialização do dispositivo:

    ![AVD verification](/DOC/assets/development-setup-28.png)
    
4. Após inicialização do dispositivo, o emulador deverá apresentar a seguinte tela:

    ![AVD verification](/DOC/assets/development-setup-29.png)

## Desenvolvimento do Aplicativo

Agora vamos utilizar o Android Studio, configurado de forma apropriada anteriormente, para desenvolvermos nosso primeiro aplicativo utilizando o SDK fornecido pela própria BYD:

### Novo Projeto Android - Hello World:

Esse é o primeiro projeto, e utilizado como exemplo, entre outros projetos e aplicativos que poderão ser desenvolvidos. O objetivo aqui é apenas desenvolver um pequeno aplicativo, muito simples, para a compilação e execução do primeiro teste de execução no veículo.

O que será feito:

> * Criação de um novo projeto no Android Studio
> * Definição do modelo de aplicativo a ser utilizado
> * Especificação das informações do aplicativo
> * Especificação do SDK Android e linguagens a serem utilizadas
> * Conclusão do novo projeto

#### Procedimento:

1. Abra o Android Studio e escolha a opção **New Project**:

    ![Desenvolvimento - New Project](/DOC/assets/development-app-01.png)

2. No lado esquerdo da janela New Project, sessão **Templates**, selecione a opção **Phone and Tablet** e depois escolha o modelo **Basic Views Activity**:

    ![Desenvolvimento - Template selection](/DOC/assets/development-app-02.png)

  > 
  > ### Nota
  > Embora o objetivo seja criar um aplicativo para um automóvel, não selecione a opção **Automotive**. Essa opção é para criar aplicativos compatíveis com o Android Auto, o que não é o nosso caso nesse exemplo.
  >

3. Clique em **Next**.

4. Na próxima tela, preencha os campos com as informações desejadas para o aplicativo:

    ![Desenvolvimento - New Project](/DOC/assets/development-app-03.png)

5. Clique em **Finish**.

6. Quando o assistente concluir a configuração do projeto, clique em **Finish** novamente:

    ![Desenvolvimento - Template selection](/DOC/assets/development-app-04.png)

7. A tela anterior será fechada e uma nova tela, já com o código do aplicativo, será mostrada:

    ![Desenvolvimento - Template selection](/DOC/assets/development-app-05.png)

### Configuração e Desenvolvimento:

O procedimento anterior criou um projeto simples, baseado nas informações que passamos para o assistente do Android Studio. O que precisamos fazer agora, é realizar as configurações apropriadas para que o projeto seja compilado da forma adequada e obtenha todas as permissões necessárias para utilizar as funções do veículo.

O que será feito:

> * Configuração do SDK correto no módulo do aplicativo;
> * 
> * 
> * 
> * 

#### Procedimento:

1. Caso não esteja visível, expanda a sessão **Gradle Scripts**, no Project Explorer:

    ![Desenvolvimento - Template selection](/DOC/assets/development-app-06.png)

2. E então dê um clique duplo para abrir o arquivo **build.gradle**, o arquivo do módulo do aplicativo (Module :app):

    ![Desenvolvimento - Template selection](/DOC/assets/development-app-07.png)

3. Localize a linha com a informação da versão do SDK a ser utilizado para a compilação do aplicativo:

    ![Desenvolvimento - Template selection](/DOC/assets/development-app-08.png)

4. Altere a versão para utilização do SDK 25:

    ``` compileSdk 25 ```

5. Faça o mesmo para o parâmetro **targetSDK**:

    ``` targetSdk 25 ```

6. Configure as dependências conforme abaixo:

    ```
    dependencies {
        implementation fileTree(include: ['*.jar'], dir: 'libs' )
        implementation 'com.android.support:appcompat—v7:25.1.0'
        implementation 'com.android.support:design:25.1.0'
        testImplementation 'junit:junit:4.12'
    }
    ```

7. Acrescente a configuração de certificado para assinatura da versão de testes (Debug) dentro da sessão **android**:

    ```
    signingConfigs {
        mydebug {
            keyAlias 'androiddebugkey'
            keyPassword 'android'
            storeFile file('./keystore/platform.keystore')
            storePassword 'android'
        }
    }
    ```

8. Por fim, inclua as informações de build para a compilação da sua versão de testes:

    ```
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
        debug {
            signingConfig signingConfigs.mydebug
        }
    }
    ```

9. O seu arquivo build.gradle deve ficar como abaixo:

    build.gradle (:app)
    ```
    apply plugin: 'com.android.application'

    android {
        signingConfigs {
            mydebug  {
                keyAlias 'androiddebugkey'
                keyPassword 'android'
                // Aqui você precisa configurar de acordo com o caminho do seu arquivo de chaves de assinatura: platform.keystore
                storeFile file('./keystore/platform.keystore')
                storePassword 'android'
            }
        }
        compileSdkVersion 25
        defaultConfig {
            applicationId "com.byd.user.helloworld"
            minSdkVersion 25
            targetSdkVersion 25
            versionCode 1
            versionName "1.0"
            testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"

        }
        buildTypes {
            release {
                minifyEnabled false
                proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            }
            debug {
                signingConfig signingConfigs.mydebug
            }

        }
    }

    dependencies {
        implementation fileTree(include: ['*.jar'], dir: 'libs')
        implementation 'com.android.support:appcompat-v7:25.1.0'
        implementation 'com.android.support.constraint:constraint-layout:1.1.0'
        implementation 'com.android.support:design:25.1.0'
        testImplementation 'junit:junit:4.12'
    }
    ```
