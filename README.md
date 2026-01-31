# API Backend
## Krav
- Java 17
- Maven
- [Frontend](https://github.com/Luythen/skapa-en-tj-nst-utifr-n-ett-api-frontend-Luythen)
- Mysql database

## url-slutpunkter
- `GET /api/initUser` Skapar en ny cookie ifall det inte finns och skickar den till frontend
- `POST /api/favorite` lägger till en ny favorit rätt
- `GET /api/favorite?filter=<filter>` hämtar alla favorit rätter och filterar datan beroende på vilket filter
- `PUT /api/favorite` ändrar commentar på den valde favorit rätt
- `DELETE /api/favorite` tabort en favorit rätt

## Filter
- `normal` hur database hämtar info
- `date` sorter baserat på datum vilken är tillag senast är först
- `name` sorterar rätterna alfabetisk

## Konfiguration
```java
spring.application.name=Backend
spring.datasource.url=jdbc:mysql://${MYSQL:localhost}:3306/backend
spring.datasource.username=Backend
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
server.port=5050

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Och vi ändring av port på frontend ändra också porten här
```java
@Bean
	public WebMvcConfigurer corsConfigurer () {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings (CorsRegistry corsRegistry) {
				corsRegistry.addMapping("/api/initUser").allowCredentials(true).allowedOrigins("http://localhost:<ändrad port>");
				corsRegistry.addMapping("/api/favorite").allowCredentials(true).allowedOrigins("http://localhost:<ändrad port>").allowedMethods("DELETE", "GET", "POST", "PUT");
			}
		};
	}
```
