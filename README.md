# openapi: 3.0.1
## info:
    title: OpenAPI definition
      version: v0
## servers:
    url: http://localhost:9091
      description: Generated server url
## tags:
    name: CUSTOMER INFO API CONTROLLER
      description: API for get Customer Info
## paths:
  ### /api/customer/v1/update:
    post:
      tags:
        CUSTOMER INFO API CONTROLLER
      summary: Update Customers Info
      description: Update Customers Info by Request
      operationId: Method PUT 'updateCustomer' for update Customer Info
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/CustomerRequestDto'
        required: true
      responses:
        '200':
          description: Customer Info update successful
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Customer not found
          content:
            '*/*':
              schema:
                type: object
  ### /api/customer/v1/{id}:
    get:
      tags:
        CUSTOMER INFO API CONTROLLER
      summary: Get Customer Info by Id
      description: Show the Customer Info found by Id
      operationId: Method GET 'getCustomerById' for get a Customer Info
      parameters:
        name: Customer Id
          in: path
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '200':
          description: Customer Info found successful
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: Customer not found
          content:
            '*/*':
              schema:
                type: object
  ### /api/customer/v1/all:
    get:
      tags:
        CUSTOMER INFO API CONTROLLER
      summary: Get All Customers Info
      description: Show all Customers Info
      operationId: Method GET 'getAllCustomers' for get all Customers Info
      responses:
        '200':
          description: All Customers Info found successful
          content:
            '*/*':
              schema:
                type: object
        '404':
          description: No Data of Customers
          content:
            '*/*':
              schema:
                type: object
## components:
  ### schemas:
    CustomerRequestDto:
      type: object
        properties:
          documentNumber:
            type: integer
            format: int64
          firstName:
            type: string
          lastName:
            type: string
          documentType:
            type: string
