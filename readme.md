### Bu projede rabbitmq işlemlerininin nasıl olacağıyla ilgili 2 örnek hazırladım. 

### RabbitMQ, bir mesaj kuyruğu (message queue) sistemidir ve çeşitli bileşenlerle birlikte çalışır. Bu bileşenler ve RabbitMQ'nun işleyişi aşağıdaki gibidir:


    1. Producer (Üretici):

Tanım: Mesajları oluşturan ve gönderen bileşendir.

İşlev: Producer, mesajı RabbitMQ'ya gönderir. Mesaj, bir veya daha fazla kuyrukta saklanmak üzere belirli bir exchange'e yönlendirilir.

    2. Consumer (Tüketici):
Tanım: Mesajları alan ve işleyen bileşendir.

İşlev: Consumer, bir veya daha fazla kuyruktan mesajları alır ve işler. RabbitMQ, mesajları consumer'a "iterek" (push) veya consumer'ın mesajları "çekmesine" (pull) izin verir.

    3. Queue (Kuyruk):

Tanım: Mesajların geçici olarak saklandığı yerdir.

İşlev: Kuyruklar, mesajların güvenli bir şekilde saklanmasını sağlar. Producer tarafından gönderilen mesajlar, consumer tarafından alınıncaya kadar burada bekler.


    4. Exchange:

Tanım: Mesajların gönderildiği ve kuyruklara yönlendirildiği yapıdır.

İşlev: Exchange, gelen mesajları belirli kurallara göre kuyruklara yönlendirir. Exchange türleri arasında direct, topic, fanout ve headers bulunur.

Bizim örneklerimiz direct exchange üzerinden olacaktır.


    5. Binding:

Tanım: Bir exchange ile bir veya daha fazla kuyruk arasındaki ilişkiyi tanımlar.

İşlev: Binding, exchange'in hangi kuyruğa veya kuyruklara mesaj göndereceğini belirler.


    6. Routing Key:

Tanım: Bir mesajın hangi kuyruğa veya kuyruklara gideceğini belirleyen etikettir.

İşlev: Producer, mesajı gönderirken bir routing key belirtir. Exchange, bu anahtarı kullanarak mesajı uygun kuyruğa yönlendirir.


### Örnek 1:

1-RabbitMqConfiguration2 sınıfı içerisinde exchange oluşturup, kuyruk ve routing keyi ilgili exchange ile bağlanma ayarlamaları yapıldı.

2-NotificationSender sınıfımız proje ayağa kalktığında producer olan NotificationProducer sınıfını kullanarak kuyruğa bir notification gönderiyor.

3-Consumer olan NotificationConsumer olan sınıfımız belirlemiş olduğumuz kuyruğu dinliyor. Buraya veri geldiği anda kuyruktan o veriyi alıp işliyor.


### Örnek 2:

#### Bu örneğimizde ilk örnekte tek exchange tek queue ve tek routing key tanımlaması yaptık. Aslında gerçek projede çok daha fazladır.

#### Şimdi bu senaryoda birden fazla kuyruğu tek bir exchange bind etmek istiyoruz ve routing key olarakta queue ismini vereceğiz. 
#### Bu kuyruk isimleri application.properties dosyasında tanımlanmaktadır.

#### Kullanıcının endpoint üzerinden bir rapor isteğinde bulunduğunu varsayıp bu işlemi kuyruk üzerinden yapacağız.

1-RabbitMqConfiguration bu sınıfımızda;

    rabbitmq.exchange.name = project-exchange
    default.queue.report=report-queue-test
    default.queue.x=queue1-test
    default.queue.y=queue2-test

default.queue prefixine sahip kuyrukları project-exchange direct exchangine bind ederek oluşturuyoruz. Routing key olarak queue isimleridir.

2-RabbitProducer sınıfımız bu kuyruklara gönderim işleminden sorumludur. 

3-ReportGenerator sınıfımızın publish methodu kuyruğa gönderme ve pull methodu da bu kuyruğu dinleme işlemlerini yerine getirir.

4-Endpoint PDFExportController sınıfından süreç başlar. 

Proje dizininde rapor kuyruktan oluşacaktır.
