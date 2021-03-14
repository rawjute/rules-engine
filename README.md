
# rules-engine

## Puropose
This library provides a Java rules engine for Spring Boot applications.


## Components
A `Rule` is just an object with the following attributes:
- `id`: a string identifier for a specific rule
- `trigger`: a string identifier that specify the trigger that fires the rule
- `conditions`: a list of boolean condition
- `action`: a list of action

A rule is triggered by an event, then its conditions are evaluated and, if all of them are satisfied, then the associated action will be executed.

The main components of this library are:
- `RulesEngine`: a Spring `Service`, representing the entry point that exposes all the main features
- `RulesBuilder`: a static class that should be used for rules creation
- `RulesDataSource`: an interface that connects the `RulesEngine` with the rules. You should provide your own implementation of the `RulesDataSource` depending on how you handle your rules, and the `RulesEngine` will use that.

## Get Started
To add the rules engine to your project you have to include it in your `pom.xml` by adding this dependency:
```xml
<dependency>  
	<groupId>com.rawjute</groupId>  
	<artifactId>rules-engine</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

Then you have to provide your own implementation of the `RulesDataSource`, by adding a configuration like the following one:
```java
@Configuration  
public class RulesDataSourceConfig {  
  
  @Bean
  public RulesDataSource getRulesDataSource() {  
	  return new MapRulesDataSource();  
  }
  
}
```

In this case, the `MapRulesDataSource` is a class that holds the rules in a list in memory, you may want to save the rules in a more structured way, such as a DataBase.

```java
public class MapRulesDataSource implements RulesDataSource {  
  
    private final List<Rule> rules = new LinkedList<>();  
  
	@Override  
	public void addRule(Rule rule) {  
		rules.add(rule);  
	}  

	@Override  
	public List<Rule> getRules() {  
		return new LinkedList<>(rules);  
	}  

	@Override  
	public void removeRule(String ruleId) {  
		rules.removeIf(r -> r.getId().equalsIgnoreCase(ruleId));  
	}
	
}
```

## Create Rules
To create new rules you should use the `RulesEngineBuilder` class, that provides a mini DSL for defining all rules attributes.

Below is an example:

```java
Rule rule = RulesEngineBuilder
	.newRule("weather-rule-1")
	.when("weather.change")
	.and(() -> true)
	.then(() -> System.out.println("Weather changed"))
	.build()
```

- `newRule` takes as argument the id of the rule
- `when` specifies the trigger for the rule
- `and` takes as an argument a boolean functional interface, that indicates a condition. You can concatenate multiple `and`
- `then` takes as an argument a functional interface that indicates an action. You can concatenate multiple `then`
- `build` creates the rule

## Triggers
The main difference between this rules engine and standard ones is that this rules engine includes the concept of `Trigger`.

With triggers you don't have to manually fire rules when event happens, but this happens automatically when an event occour, by simply adding an annotation `@Trigger("event")` to a method of a Spring Component.

To achieve this feature the rules engine uses the concept of **Aspect Oriented Programming** (AOP), executing some extra-code after the annotated method has been called.

By putting the `Trigger` annotation to a method, you are telling to the rules engine that that method is a trigger for the evaluation of certain rules, and the `value` of the annotation represent the only trigger attribute.

Take a look at the following example:

```java
@Trigger("weather.change")  
public void weatherChanged() {  
	System.out.println("Weather rule test");
}
```

This method is annotated with a trigger, but nothing happens until we set an associated rule, so that when `weatherChanged()` method is invoked we will see only this print:
> `Weather rule test`

But magic happens if we add the following code:
```java
Rule rule = RulesEngineBuilder
	.newRule("weather-rule-1")
	.when("weather.change")
	.and(() -> true)
	.then(() -> System.out.println("Weather changed"))
	.build()

rulesEngine.saveRule(rule);
```

Now every time the `weatherChanged()` method is invoked, we will see this prints:
> `Weather rule test`
> `Weather changed`
