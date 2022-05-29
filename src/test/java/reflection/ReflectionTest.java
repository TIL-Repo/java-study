package reflection;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import reflection._1_simple_example.Person;
import reflection._2_inspecting_java_classes.getting_ready.Goat;
import reflection._3_inspecting_constructors.Bird;

public class ReflectionTest {

	/* Simple Example */

	@Test
	public void simpleTest() throws Exception {
	    //given
		Object person = new Person();
		//when
		Field[] fields = person.getClass().getDeclaredFields();
		final List<String> actualFieldNames = getFieldNames(fields);
		//then
		assertTrue(Arrays.asList("name", "age").containsAll(actualFieldNames));
	}

	private static List<String> getFieldNames(Field[] fields) {
		List<String> fieldNames = new ArrayList<>();
		for (Field field : fields) {

			fieldNames.add(field.getName());
		}
		return fieldNames;
	}

	/* Getting Ready */

	@Test
	public void getClassNames() throws Exception {
	    //given
		Object goat = new Goat("goat");
		//when
		Class<?> clazz = goat.getClass();
		//then
		assertEquals("Goat", clazz.getSimpleName());
		assertEquals("reflection._2_inspecting_java_classes.getting_ready.Goat", clazz.getName());
		assertEquals("reflection._2_inspecting_java_classes.getting_ready.Goat", clazz.getCanonicalName());

		//when
		Class<?> clazz2 = Class.forName("reflection._2_inspecting_java_classes.getting_ready.Goat");
		//then
		assertEquals("Goat", clazz2.getSimpleName());
		assertEquals("reflection._2_inspecting_java_classes.getting_ready.Goat", clazz2.getName());
		assertEquals("reflection._2_inspecting_java_classes.getting_ready.Goat", clazz2.getCanonicalName());
	}
	
	@Test
	public void getClassModifiers() throws Exception {
	    //given
		Class<?> goatClass = Class.forName("reflection._2_inspecting_java_classes.getting_ready.Goat");
		Class<?> animalClass = Class.forName("reflection._2_inspecting_java_classes.getting_ready.Animal");
		//when
		int goatMods = goatClass.getModifiers();
		int animalMods = animalClass.getModifiers();
		//then
		assertTrue(Modifier.isPublic(goatMods));
		assertFalse(Modifier.isInterface(animalMods));
		assertTrue(Modifier.isPublic(animalMods));
		assertTrue(Modifier.isAbstract(animalMods));
	}

	@Test
	public void getPackageInformation() throws Exception {
	    //given
		Goat goat = new Goat("goat");
		Class<?> goatClass = goat.getClass();
		//when
		Package pkg = goatClass.getPackage();
		//then
		assertEquals("reflection._2_inspecting_java_classes.getting_ready", pkg.getName());
	}

	@Test
	public void getSuperClass() throws Exception {
	    //given
		final Goat goat = new Goat("goat");
		String str = "any string";
		//when
		final Class<?> goatSuperClass = goat.getClass().getSuperclass();
		//then
		assertEquals("Animal", goatSuperClass.getSimpleName());
		assertEquals("Object", str.getClass().getSuperclass().getSimpleName());
	}

	/* Implemented Interfaces */

	@Test
	public void getImplementedInterfaces() throws Exception {
	    //given
		Class<?> goatClass = Class.forName("reflection._2_inspecting_java_classes.getting_ready.Goat");
		Class<?> animalClass = Class.forName("reflection._2_inspecting_java_classes.getting_ready.Animal");
	    //when
		final Class<?>[] goatInterfaces = goatClass.getInterfaces();
		final Class<?>[] animalInterfaces = animalClass.getInterfaces();
		//then
		assertEquals(1, goatInterfaces.length);
		assertEquals(1, animalInterfaces.length);
		assertEquals("Locomotion", goatInterfaces[0].getSimpleName());
		assertEquals("Eating", animalInterfaces[0].getSimpleName());
	}

	@Test
	public void getConstructorsMethodsAndFields() throws Exception {

		final Class<?> goatClass = Class.forName("reflection._2_inspecting_java_classes.getting_ready.Goat");
		final Class<?> animalClass = Class.forName("reflection._2_inspecting_java_classes.getting_ready.Animal");

		/* Constructor */
		//when
		final Constructor<?>[] constructors = goatClass.getConstructors();
		//then
		assertEquals(1, constructors.length);
		assertEquals("reflection._2_inspecting_java_classes.getting_ready.Goat", constructors[0].getName());

		/* Field */
		//when
		final Field[] fields = animalClass.getDeclaredFields();
		final List<String> actualFields = getFieldNames(fields);
		//then
		assertEquals(2, actualFields.size());
		assertTrue(actualFields.containsAll(Arrays.asList("name", "CATEGORY")));

		/* Method */
		//when
		final Method[] methods = animalClass.getDeclaredMethods();
		final List<String> actualMethods = getMethodNames(methods);
		//then
		assertEquals(3, actualMethods.size());
		assertTrue(actualMethods.containsAll(Arrays.asList("getName", "setName", "getSound")));
	}

	private static List<String> getMethodNames(Method[] methods) {
		List<String> methodNames = new ArrayList<>();
		for (Method method : methods)
			methodNames.add(method.getName());
		return methodNames;
	}

	@Test
	public void getConstructor() throws Exception {
	    //given
		final Class<?> birdClass = Class.forName("reflection._3_inspecting_constructors.Bird");
		//when
		final Constructor<?> cons1 = birdClass.getConstructor();
		final Constructor<?> cons2 = birdClass.getConstructor(String.class);
		final Constructor<?> cons3 = birdClass.getConstructor(String.class, boolean.class);
		// final Constructor<?> cons4 = birdClass.getConstructor(String.class, Boolean.class); // NoSuchMethodException
	}

	@Test
	public void createNewInstance() throws Exception {
		//given
		final Class<?> birdClass = Class.forName("reflection._3_inspecting_constructors.Bird");
		final Constructor<?> cons1 = birdClass.getConstructor();
		final Constructor<?> cons2 = birdClass.getConstructor(String.class);
		final Constructor<?> cons3 = birdClass.getConstructor(String.class, boolean.class);

		//when
		final Bird bird = (Bird)cons1.newInstance();
		final Bird bird2 = (Bird)cons2.newInstance("Weaver bird");
		final Bird bird3 = (Bird)cons3.newInstance("dove", true);

		//then
		assertEquals("bird", bird.getName());
		assertEquals("Weaver bird", bird2.getName());
		assertEquals("dove", bird3.getName());

		assertFalse(bird.walks());
		assertTrue(bird3.walks());
	}

	/* Inspecting Fields */

	@Test
	public void getPublicFields() throws Exception {
	    //given
		final Class<?> birdClass = Class.forName("reflection._3_inspecting_constructors.Bird");
		//when
		final Field[] fields = birdClass.getFields();
	    //then
		assertEquals(1, fields.length);
		assertEquals("CATEGORY", fields[0].getName());
	}

	@Test
	public void getPublicFieldByName() throws Exception {
	    //given
		final Class<?> birdClass = Class.forName("reflection._3_inspecting_constructors.Bird");
		//when
		final Field field = birdClass.getField("CATEGORY");
		//then
		assertEquals("CATEGORY", field.getName());
	}

	@Test
	public void getDeclaredFields() throws Exception {
	    //given
		final Class<?> birdClass = Class.forName("reflection._3_inspecting_constructors.Bird");
		//when
		final Field[] fields = birdClass.getDeclaredFields();
		//then
		assertEquals(1, fields.length);
		assertEquals("walks", fields[0].getName());
	}

	@Test
	public void setsAndGetValue() throws Exception {
	    //given
		final Class<?> birdClass = Class.forName("reflection._3_inspecting_constructors.Bird");
		final Bird bird = (Bird)birdClass.getConstructor().newInstance();
		final Field field = birdClass.getDeclaredField("walks");
		//when, then
		field.setAccessible(true);

		assertFalse(field.getBoolean(bird));
		assertFalse(bird.walks());

		field.set(bird, true);

		assertTrue(field.getBoolean(bird));
		assertTrue(bird.walks());
	}

	@Test
	public void getsAndSetsWithNull() throws Exception {
	    //given
		final Class<?> birdClass = Class.forName("reflection._3_inspecting_constructors.Bird");
		final Field field = birdClass.getField("CATEGORY");
		//when
		field.setAccessible(true);
	    //then
		assertEquals("domestic", field.get(null));
	}

	/* Inspecting Methods */

	@Test
	public void getsAllPublicMethods() throws Exception {
	    //given
		final Class<?> birdClass = Class.forName("reflection._3_inspecting_constructors.Bird");
		//when
		final Method[] methods = birdClass.getMethods();
		final List<String> methodNames = getMethodNames(methods);
		//then
		assertTrue(methodNames.containsAll(Arrays.asList(
			"equals", "notifyAll", "hashCode", "walks", "eats", "toString"
		)));
	}

	@Test
	public void getOnlyDeclaredMethods() throws Exception {
	    //given
		final Class<?> birdClass = Class.forName("reflection._3_inspecting_constructors.Bird");
		List<String> expectedMethodNames = Arrays.asList("setWalks", "walks", "getSound", "eats");
		//when
		final List<String> actualMethodNames = getMethodNames(birdClass.getDeclaredMethods());
		//then
		assertEquals(expectedMethodNames.size(), actualMethodNames.size());
		assertTrue(expectedMethodNames.containsAll(actualMethodNames));
		assertTrue(actualMethodNames.containsAll(expectedMethodNames));
	}

	@Test
	public void getsMethod() throws Exception {
	    //given
		final Bird bird = new Bird();
		final Method walksMethod = bird.getClass().getDeclaredMethod("walks");
		final Method setWalksMethod = bird.getClass().getDeclaredMethod("setWalks", boolean.class);
		//when, then
		assertTrue(walksMethod.canAccess(bird));
		assertTrue(setWalksMethod.canAccess(bird));
	}

	@Test
	public void invokes() throws Exception {
	    //given
		final Class<?> birdClass = Class.forName("reflection._3_inspecting_constructors.Bird");
		final Bird bird = (Bird)birdClass.getConstructor().newInstance();
		final Method setWalksMethod = birdClass.getDeclaredMethod("setWalks", boolean.class);
		final Method walksMethod = birdClass.getDeclaredMethod("walks");
		final boolean walks = (boolean)walksMethod.invoke(bird);
		//when, then
		assertFalse(walks);
		assertFalse(bird.walks());

		setWalksMethod.invoke(bird, true);

		final boolean walks2 = (boolean)walksMethod.invoke(bird);
		assertTrue(walks2);
		assertTrue(bird.walks());
	}
}
