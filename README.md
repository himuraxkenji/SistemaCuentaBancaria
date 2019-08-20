# SistemaCuentaBancaria
El siguiente sistema es una guia para el uso de clean arquitecture.


# JPA 
Las 4 formas de generar el id mediante jpa son:
* GenerationType.AUTO
* GenerationType.IDENTITY
* GenerationType.SEQUENCE
* GenerationType.TABLE

## GenerationType.AUTO
Es el tipo de generación predeterminado y permite al proveedor de persistencia elegir la estrategia de generación para el id.

``` java
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id", updatable = false, nullable = false)
private Long id;
```
Si se emplea Hibernate como proveedor de persistencia, este selecciona una estrategia de generación basada en el dialecto específico de la base de datos. Para las bases de datos más populares, selecciona GenerationType.SEQUENCE.

## GenerationType.IDENTITY

GenerationType.IDENTITY es el más fácil de usar pero no el mejor desde el punto de vista del rendimiento. Se basa en una columna de base de datos autoincremental y permite que la base de datos genere un nuevo valor con cada operación de inserción. Desde el punto de vista de la base de datos, esto es muy eficiente porque las columnas de incremento automático están altamente optimizadas y no requiere ninguna declaración adicional.
``` java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", updatable = false, nullable = false)
private Long id;
```
Este enfoque tiene un inconveniente importante si usa Hibernate. Hibernate requiere un valor de clave principal para cada entidad administrada y, por lo tanto, debe realizar la instrucción de inserción de inmediato. Esto evita que utilice diferentes técnicas de optimización como el procesamiento por lotes JDBC.

## GenerationType.SEQUENCE

GenerationType.SEQUENCE utiliza una secuencia de base de datos para generar valores únicos.

Requiere sentencias select adicionales para obtener el siguiente valor de una secuencia de base de datos. Pero esto no tiene impacto en el rendimiento para la mayoría de las aplicaciones. Y si su aplicación tiene que persistir una gran cantidad de nuevas entidades, puede usar algunas optimizaciones específicas de Hibernate para reducir la cantidad de declaraciones.
``` java
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
@SequenceGenerator(name="book_generator", sequenceName = "book_seq", allocationSize=50)
@Column(name = "id", updatable = false, nullable = false)
private Long id;
```

## GenerationType
El GenerationType.TABLE se usa raramente hoy en día. Simula una secuencia almacenando y actualizando su valor actual en una tabla de base de datos que requiere el uso de bloqueos que colocan todas las transacciones en un orden secuencial. Esto ralentiza su aplicación y, por lo tanto, debe preferir GenerationType.SEQUENCE, si su base de datos admite secuencias, lo que hacen las bases de datos más populares.

``` java
@Id
@GeneratedValue(strategy = GenerationType.TABLE, generator = "book_generator")
@TableGenerator(name="book_generator", table="id_generator", schema="bookstore")
@Column(name = "id", updatable = false, nullable = false)
private Long id;
```
