package io.github.mwttg.pixel.artillery.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class ReadFileTest {

  @Test
  public void testJsonFromResources_validFile() {
    final var actual = ReadFile.jsonFromResources("files/valid-file.json", TestJson.class);
    final var expected = new TestJson("Hello World!", 42);
    assertThat(actual).isEqualTo(expected);
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testJsonFromResources_invalidFile() {
    ReadFile.jsonFromResources("files/invalid-file.json", TestJson.class);
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void testJsonFromResources_missingFile() {
    ReadFile.jsonFromResources("files/missing.json", TestJson.class);
  }

  @Test
  public void testFromResources_valid() {
    final var actual = ReadFile.fromResources("/files/valid-textfile.txt");
    assertThat(actual).containsExactly("1. line", "2. line", "3. line");
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void testFromResource_missingFile() {
    ReadFile.fromResources("/files/missing.txt");
  }

  @Test
  public void testJsonFrom_validFile() {
    final var currentDirectory = System.getProperty("user.dir");
    final var sub = "/src/test/resources/files/valid-file.json";
    final var filename = currentDirectory + sub;
    final var actual = ReadFile.jsonFrom(filename, TestJson.class);
    final var expected = new TestJson("Hello World!", 42);
    assertThat(actual).isEqualTo(expected);
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testJsonFrom_invalidFile() {
    final var currentDirectory = System.getProperty("user.dir");
    final var sub = "/src/test/resources/files/invalid-file.json";
    final var filename = currentDirectory + sub;
    ReadFile.jsonFrom(filename, TestJson.class);
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testJsonFrom_missingFile() {
    final var currentDirectory = System.getProperty("user.dir");
    final var sub = "/src/test/resources/files/missing-file.json";
    final var filename = currentDirectory + sub;
    ReadFile.jsonFrom(filename, TestJson.class);
  }
}