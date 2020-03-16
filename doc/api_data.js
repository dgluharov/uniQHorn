define({ "api": [
  {
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "varname1",
            "description": "<p>No type.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "varname2",
            "description": "<p>With type.</p>"
          }
        ]
      }
    },
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "./apidoc/main.js",
    "group": "C__Work_UniQHorn_uniQHorn_apidoc_main_js",
    "groupTitle": "C__Work_UniQHorn_uniQHorn_apidoc_main_js",
    "name": ""
  },
  {
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "varname1",
            "description": "<p>No type.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "varname2",
            "description": "<p>With type.</p>"
          }
        ]
      }
    },
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "./doc/main.js",
    "group": "C__Work_UniQHorn_uniQHorn_doc_main_js",
    "groupTitle": "C__Work_UniQHorn_uniQHorn_doc_main_js",
    "name": ""
  },
  {
    "type": "post",
    "url": "/leaves/create/:username",
    "title": "Create leave",
    "name": "createLeave",
    "group": "Leave",
    "permission": [
      {
        "name": "User"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>username</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "hours",
            "description": "<p>Leave hours - can be 4 or 8</p>"
          },
          {
            "group": "Parameter",
            "type": "Date",
            "optional": false,
            "field": "date",
            "description": "<p>Date for the leave</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>Type of leave - PAIN, UNPAID, SICK, SPECIAL</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "HTTPResp",
            "optional": false,
            "field": "resp",
            "description": "<p>HTTP 200 OK</p>"
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "HTTP400BadRequest",
            "description": "<p>Incorrect data format</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "HTTP409Conflict",
            "description": "<p>Existing user</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./src/main/java/com/uniqhorn/controller/LeavesController.java",
    "groupTitle": "Leave"
  },
  {
    "type": "get",
    "url": "/login",
    "title": "Log in as an user",
    "name": "process",
    "group": "LogIn",
    "permission": [
      {
        "name": "none"
      }
    ],
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Authorization",
            "description": "<p>Username and password with Basic auth.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Header-Example:",
          "content": "{\n\t\"Authorization\": \"Basic YWRtaW5AZ21haWwuY29tOkFkbWluMQ==\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Cookie",
            "optional": false,
            "field": "Cookie",
            "description": "<p>Cookie with SID.</p>"
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "HTTP400BadRequest",
            "description": "<p>Incorrect data format</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./src/main/java/com/uniqhorn/controller/LoginController.java",
    "groupTitle": "LogIn"
  },
  {
    "type": "post",
    "url": "/users",
    "title": "Create user",
    "name": "createUser",
    "group": "User",
    "permission": [
      {
        "name": "ADMIN"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>User email.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>User password.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "firstName",
            "description": "<p>First name.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "lastName",
            "description": "<p>Last name.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "position",
            "description": "<p>Position e.g. QA, SysAdmin, MD.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "status",
            "description": "<p>Status e.g. InOffice, InClientOffice-KOSTAL.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>Phone number.</p>"
          },
          {
            "group": "Parameter",
            "type": "Date",
            "optional": false,
            "field": "start",
            "description": "<p>Start date.</p>"
          },
          {
            "group": "Parameter",
            "type": "String[]",
            "optional": false,
            "field": "roles",
            "description": "<p>Roles array.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": " {\n\t\t\"username\" : \"test2@gmail.com\",\n\t\t\"password\" : \"Test123\",\n\t\t\"firstName\": \"John\",\n\t\t\"lastName\" : \"Doe\",\n\t\t\"position\" : \"Tester\",\n\t\t\"status\"   : \"inOffice\",\n\t\t\"phoneNumber\" : \"+359876278593\",\n\t\t\"startDate\" : \"2018-01-30\",\n\t\t\"roles\" : [{\n\t\t\t\"role\":\"MASTER\"\n\t\t}]\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>username</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>password</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "firstName",
            "description": "<p>first name</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "lastName",
            "description": "<p>last name</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "position",
            "description": "<p>position</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "status",
            "description": "<p>status</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "phoneNumber",
            "description": "<p>phone number</p>"
          },
          {
            "group": "Success 200",
            "type": "Date",
            "optional": false,
            "field": "startDate",
            "description": "<p>start date</p>"
          },
          {
            "group": "Success 200",
            "type": "String[]",
            "optional": false,
            "field": "roles",
            "description": "<p>roles</p>"
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "HTTP400BadRequest",
            "description": "<p>Incorrect data format</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "HTTP409Conflict",
            "description": "<p>Existing user</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./src/main/java/com/uniqhorn/controller/UserController.java",
    "groupTitle": "User"
  },
  {
    "type": "delete",
    "url": "/users/:username",
    "title": "Delete a user by username",
    "name": "deleteByUsername",
    "group": "User",
    "permission": [
      {
        "name": "ADMIN"
      }
    ],
    "version": "0.0.0",
    "filename": "./src/main/java/com/uniqhorn/controller/UserController.java",
    "groupTitle": "User"
  },
  {
    "type": "get",
    "url": "/users",
    "title": "Get all users",
    "name": "getAllUsers",
    "group": "User",
    "permission": [
      {
        "name": "ADMIN"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>username</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>password</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "firstName",
            "description": "<p>first name</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "lastName",
            "description": "<p>last name</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "position",
            "description": "<p>position</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "status",
            "description": "<p>status</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "phoneNumber",
            "description": "<p>phone number</p>"
          },
          {
            "group": "Success 200",
            "type": "Date",
            "optional": false,
            "field": "startDate",
            "description": "<p>start date</p>"
          },
          {
            "group": "Success 200",
            "type": "String[]",
            "optional": false,
            "field": "roles",
            "description": "<p>roles</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Example:",
          "content": "   [{\n    \"id\": 1,\n    \"username\": \"John@gmail.com\",\n    \"password\": \"$2a$10$1mnewXZqa0m.V4peRkt2xuniYRC7bxu6.1cjFJ8SNx3MBON80vIky\",\n    \"firstName\": \"John\",\n    \"lastName\": \"Doe\",\n    \"position\": \"Admin\",\n    \"status\": \"Office\",\n    \"phoneNumber\": \"1232531\",\n    \"roles\": [\n        {\n            \"role_id\": 2,\n            \"role\": \"ADMIN\"\n        }\n    ],\n    \"leaves\": [],\n    \"startDate\": \"2018-03-20\"\n},\n{\n    \"id\": 2,\n    \"username\": \"Jane@gmail.com\",\n    \"password\": \"$2a$10$1mnewXZqa0m.V4peRkt2xuniYRC7bxu6.1cjFJ8SNx3MBON80vIky\",\n    \"firstName\": \"Jane\",\n    \"lastName\": \"Doe\",\n    \"position\": \"Admin\",\n    \"status\": \"Office\",\n    \"phoneNumber\": \"1232531\",\n    \"roles\": [\n        {\n            \"role_id\": 2,\n            \"role\": \"ADMIN\"\n        }\n    ],\n    \"leaves\": [],\n    \"startDate\": \"2018-03-20\"\n}]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./src/main/java/com/uniqhorn/controller/UserController.java",
    "groupTitle": "User"
  },
  {
    "type": "get",
    "url": "/users/:username",
    "title": "Get a user by username",
    "name": "getUserByUsername",
    "group": "User",
    "permission": [
      {
        "name": "ADMIN"
      }
    ],
    "description": "<p>This method is getting a User when the parameter is passed in the URL The method will be changed (probably), to send the username via JSON Object</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>username</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>password</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "firstName",
            "description": "<p>first name</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "lastName",
            "description": "<p>last name</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "position",
            "description": "<p>position</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "status",
            "description": "<p>status</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "phoneNumber",
            "description": "<p>phone number</p>"
          },
          {
            "group": "Success 200",
            "type": "Date",
            "optional": false,
            "field": "startDate",
            "description": "<p>start date</p>"
          },
          {
            "group": "Success 200",
            "type": "String[]",
            "optional": false,
            "field": "roles",
            "description": "<p>roles</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Example:",
          "content": "   {\n    \"id\": 1,\n    \"username\": \"John@gmail.com\",\n    \"password\": \"$2a$10$1mnewXZqa0m.V4peRkt2xuniYRC7bxu6.1cjFJ8SNx3MBON80vIky\",\n    \"firstName\": \"John\",\n    \"lastName\": \"Doe\",\n    \"position\": \"Admin\",\n    \"status\": \"Office\",\n    \"phoneNumber\": \"1232531\",\n    \"roles\": [\n        {\n            \"role_id\": 2,\n            \"role\": \"ADMIN\"\n        }\n    ],\n    \"leaves\": [],\n    \"startDate\": \"2018-03-20\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./src/main/java/com/uniqhorn/controller/UserController.java",
    "groupTitle": "User"
  },
  {
    "type": "patch",
    "url": "/users/:username",
    "title": "Update user data",
    "name": "updateByUsername",
    "group": "User",
    "permission": [
      {
        "name": "ADMIN"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>User email.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>User password.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "firstName",
            "description": "<p>First name.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "lastName",
            "description": "<p>Last name.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "position",
            "description": "<p>Position e.g. QA, SysAdmin, MD.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "status",
            "description": "<p>Status e.g. InOffice, InClientOffice-KOSTAL.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>Phone number.</p>"
          },
          {
            "group": "Parameter",
            "type": "Date",
            "optional": false,
            "field": "start",
            "description": "<p>Start date.</p>"
          },
          {
            "group": "Parameter",
            "type": "String[]",
            "optional": false,
            "field": "roles",
            "description": "<p>Roles array.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": " {\n\t\t\"username\" : \"test2@gmail.com\",\n\t\t\"password\" : \"Test123\",\n\t\t\"firstName\": \"John\",\n\t\t\"lastName\" : \"Doe\",\n\t\t\"position\" : \"Tester\",\n\t\t\"status\"   : \"inOffice\",\n\t\t\"phoneNumber\" : \"+359876278593\",\n\t\t\"startDate\" : \"2018-01-30\",\n\t\t\"roles\" : [{\n\t\t\t\"role\":\"MASTER\"\n\t\t}]\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "username",
            "description": "<p>username</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>password</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "firstName",
            "description": "<p>first name</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "lastName",
            "description": "<p>last name</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "position",
            "description": "<p>position</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "status",
            "description": "<p>status</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "phoneNumber",
            "description": "<p>phone number</p>"
          },
          {
            "group": "Success 200",
            "type": "Date",
            "optional": false,
            "field": "startDate",
            "description": "<p>start date</p>"
          },
          {
            "group": "Success 200",
            "type": "String[]",
            "optional": false,
            "field": "roles",
            "description": "<p>roles</p>"
          }
        ]
      }
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "HTTP400BadRequest",
            "description": "<p>Incorrect data format</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./src/main/java/com/uniqhorn/controller/UserController.java",
    "groupTitle": "User"
  }
] });
