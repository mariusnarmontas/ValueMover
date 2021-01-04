# Value Mover

This library simply copies parameters' values from one object to another object of the same type.

For example, on JPA entity update:
```java
Person fetchedObject = personDao.findById(id);
Person receivedObjec = deserializePostRequestBody(requestBody);
```
*instead of doing this:*
```java
fetchedObject.setGender(receivedObject.getGender());
fetchedObject.setName(receivedObject.getName());
fetchedObject.setDescription(receivedObject.getDescription());
fetchedObject.setOccupation(receivedObject.getOccupation());
fetchedObject.setSalary(receivedObject.getSalary());
fetchedObject.setBloodType(receivedObject.getBloodType());
// ...
// etc.

personService.save(fetchedObject);
```
*you can do this:*
```java
MoveValues.forType(Person.class)
    .takeValuesFrom(receivedObject)
    .putValuesTo(fetchedObject)
    .moveAll();

personService.save(fetchedObject);
```

In the example above all parameters' values (parent objects included) will be copied from `receivedObject` 
to `fetchedObject`.

If values from parent object should be ignored, then `forFlatType(Class<?> type)` method should be invoked:
```java
MoveValues.forFlatType(Person.class)
    .takeValuesFrom(receivedObject)
    .putValuesTo(fetchedObject)
    .moveAll();

personService.save(fetchedObject);
```

If not all parameters' values should be copied, then instead of `moveAll()` method, 
`moveExcept(String... ignoredFieldNames)` should be invoked. Or entity class parameters should be annotated 
with `@IgnoreValue` annotation.

## Download
`ValueMover` jar file is available [here](http://narmontas.info/java_projects/value_mover/value-mover-0.2.0.jar).