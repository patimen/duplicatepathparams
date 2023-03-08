# Micronaut OpenAPI generation Bug
If:
1. A path parameter is caught using a `RequestBean`
2. The `RequestBean` variable is not named the same as the parameter
3. The containing Controller inherits from ANY class

THEN:
The path parameter shows up TWICE in the openapi:

This project outputs the following swagger file:
```openapi
openapi: 3.0.1
paths:
  /inherits/{path}/simple:
    get:
      operationId: getPath
      parameters:
      - name: path
        in: path
        required: true
        schema:
          type: string
      - name: path
        in: path
        required: true
        schema:
          type: string
      responses:
        "200":
          description: getPath 200 response
          content:
            application/json:
              schema:
                type: string
  /{path}/simple:
    get:
      operationId: getPath_1
      parameters:
      - name: path
        in: path
        required: true
        schema:
          type: string
      responses:
        "200":
          description: getPath_1 200 response
          content:
            application/json:
              schema:
                type: string

```