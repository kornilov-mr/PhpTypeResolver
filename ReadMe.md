# PHP Type Resolver

### Task on hand:
We are given a PHP Documentation blocks, where the type of a variable is described. 
**DocBlock** should contain @var tag as well as type and variable name.
In some cases the variable name couldn't be specified.
Then the type is applied to the all variables past the docblock.

There can be multiple types for one variable – these should be combined into a UnionType.

The following types are allowed:
- Basic types such as int, string, etc.
- Class types
- UnionTypes (one variable can have multiple types)
- Mixed (used for variables without type)

## API Description

**PhpVariable:**

- getDocBlock(): Returns the PhpDocBlock object, or null.

- getName(): Returns the variable name (e.g., "$user").

**PhpDocBlock:**

- getTagsByName(String tagName): Returns a list of DocTag objects for a specific tag.

**DocTag:**

- getValue(): Returns the full text content (e.g., "User $admin").

**TypeFactory:**

- createType(String typeName): Converts a string to a PhpType.

- createUnionType(List<PhpType> types): Combines multiple PhpType objects into a UnionType.

## Tests
There are 7 tests for all types and cases specified in the task description.
All tests are located in the tests folder.

