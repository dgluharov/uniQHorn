define({ "api": [
  {
    "type": "post",
    "url": "/client",
    "title": "Create client",
    "name": "createClient",
    "group": "Client",
    "permission": [
      {
        "name": "Master"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "clientName",
            "description": "<p>Client name.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "clientName",
            "description": "<p>Client name</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>ID</p>"
          },
          {
            "group": "Success 200",
            "optional": false,
            "field": "HTTP200OK",
            "description": "<p>ok ok</p>"
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
    "filename": "src/main/java/com/uniqhorn/controller/ClientController.java",
    "groupTitle": "Client"
  },
  {
    "type": "delete",
    "url": "/client/clientName",
    "title": "Delete client",
    "name": "deleteClient",
    "group": "Client",
    "permission": [
      {
        "name": "All"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "clientName",
            "description": "<p>url param - Client id.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "message",
            "description": "<p>Client deleted</p>"
          },
          {
            "group": "Success 200",
            "optional": false,
            "field": "HTTP200OK",
            "description": "<p>ok ok</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/uniqhorn/controller/ClientController.java",
    "groupTitle": "Client"
  },
  {
    "type": "get",
    "url": "/client/clientName",
    "title": "Get client",
    "name": "getClient",
    "group": "Client",
    "permission": [
      {
        "name": "All"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "clientName",
            "description": "<p>url param - Client name.</p>"
          }
        ]
      }
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
            "field": "clientName",
            "description": "<p>clientName</p>"
          },
          {
            "group": "Success 200",
            "optional": false,
            "field": "HTTP200OK",
            "description": "<p>ok ok</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/uniqhorn/controller/ClientController.java",
    "groupTitle": "Client"
  },
  {
    "type": "get",
    "url": "/client",
    "title": "Get clients",
    "name": "getClients",
    "group": "Client",
    "permission": [
      {
        "name": "All"
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
            "field": "clientName",
            "description": "<p>clientName</p>"
          },
          {
            "group": "Success 200",
            "optional": false,
            "field": "HTTP200OK",
            "description": "<p>ok ok</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/uniqhorn/controller/ClientController.java",
    "groupTitle": "Client"
  },
  {
    "type": "patch",
    "url": "/client",
    "title": "patch client",
    "name": "patchClient",
    "group": "Client",
    "permission": [
      {
        "name": "Master"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "clientName",
            "description": "<p>Client name.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "clientName",
            "description": "<p>Client name</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>ID</p>"
          },
          {
            "group": "Success 200",
            "optional": false,
            "field": "HTTP200OK",
            "description": "<p>ok ok</p>"
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
    "filename": "src/main/java/com/uniqhorn/controller/ClientController.java",
    "groupTitle": "Client"
  },
  {
    "type": "patch",
    "url": "/status/id",
    "title": "Patch Leaves for user",
    "name": "PatchLeaves",
    "group": "Leave",
    "permission": [
      {
        "name": "User"
      }
    ],
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
    "filename": "src/main/java/com/uniqhorn/controller/LeavesController.java",
    "groupTitle": "Leave"
  },
  {
    "type": "patch",
    "url": "/leaves/update/:id",
    "title": "Create Leave",
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
            "type": "Number",
            "optional": false,
            "field": "hours",
            "description": "<p>Hours.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "leaveDate",
            "description": "<p>Leave date.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "leaveType",
            "description": "<p>leave type.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "HTTP200OK",
            "description": "<p>ok ok</p>"
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
    "filename": "src/main/java/com/uniqhorn/controller/LeavesController.java",
    "groupTitle": "Leave"
  },
  {
    "type": "post",
    "url": "/leaves/create/:username",
    "title": "Create Leave",
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
            "type": "Number",
            "optional": false,
            "field": "hours",
            "description": "<p>Hours.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "leaveDate",
            "description": "<p>Leave date.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "leaveType",
            "description": "<p>leave type.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "HTTP200OK",
            "description": "<p>ok ok</p>"
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
    "filename": "src/main/java/com/uniqhorn/controller/LeavesController.java",
    "groupTitle": "Leave"
  },
  {
    "type": "delete",
    "url": "/leaves/username",
    "title": "Delete Leaves for user",
    "name": "deleteLeaves",
    "group": "Leave",
    "permission": [
      {
        "name": "Master"
      }
    ],
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
    "filename": "src/main/java/com/uniqhorn/controller/LeavesController.java",
    "groupTitle": "Leave"
  },
  {
    "type": "delete",
    "url": "/leaves/id",
    "title": "Delete Leaves for user",
    "name": "deleteLeaves",
    "group": "Leave",
    "permission": [
      {
        "name": "Master"
      }
    ],
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
    "filename": "src/main/java/com/uniqhorn/controller/LeavesController.java",
    "groupTitle": "Leave"
  },
  {
    "type": "get",
    "url": "/leaves/username",
    "title": "Get Leaves for user",
    "name": "getLeaves",
    "group": "Leave",
    "permission": [
      {
        "name": "Master"
      }
    ],
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
    "filename": "src/main/java/com/uniqhorn/controller/LeavesController.java",
    "groupTitle": "Leave"
  },
  {
    "type": "get",
    "url": "/leaves",
    "title": "Get Leave",
    "name": "getLeaves",
    "group": "Leave",
    "permission": [
      {
        "name": "Master"
      }
    ],
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
    "filename": "src/main/java/com/uniqhorn/controller/LeavesController.java",
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
    "filename": "src/main/java/com/uniqhorn/controller/LoginController.java",
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
          },
          {
            "group": "Parameter",
            "type": "Double",
            "optional": false,
            "field": "totalLeavesDays.",
            "description": "<p>totalLeavesDays</p>"
          },
          {
            "group": "Parameter",
            "type": "Double",
            "optional": false,
            "field": "leftLeavesDays.",
            "description": "<p>leftLeavesDays</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example: { \"username\" : \"test2@gmail.com\",",
          "content": "\"password\" : \"Test123\", \"firstName\": \"John\", \"lastName\" :\n\"Doe\", \"position\" : \"Tester\", \"status\" : \"inOffice\",\n\"phoneNumber\" : \"+359876278593\", \"startDate\" : \"2018-01-30\",\n\"roles\" : [{ \"role\":\"MASTER\" }] }",
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
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "totalLeavesDays.",
            "description": "<p>totalLeavesDays</p>"
          },
          {
            "group": "Success 200",
            "type": "Double",
            "optional": false,
            "field": "leftLeavesDays.",
            "description": "<p>leftLeavesDays</p>"
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
    "filename": "src/main/java/com/uniqhorn/controller/UserController.java",
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
    "filename": "src/main/java/com/uniqhorn/controller/UserController.java",
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
          "title": "Success-Example: [{ \"id\": 1, \"username\":",
          "content": "\"John@gmail.com\", \"password\":\n\"$2a$10$1mnewXZqa0m.V4peRkt2xuniYRC7bxu6.1cjFJ8SNx3MBON80vIky\",\n\"firstName\": \"John\", \"lastName\": \"Doe\", \"position\":\n\"Admin\", \"status\": \"Office\", \"phoneNumber\": \"1232531\",\n\"roles\": [ { \"role_id\": 2, \"role\": \"ADMIN\" } ], \"leaves\":\n[], \"startDate\": \"2018-03-20\" }, { \"id\": 2, \"username\":\n\"Jane@gmail.com\", \"password\":\n\"$2a$10$1mnewXZqa0m.V4peRkt2xuniYRC7bxu6.1cjFJ8SNx3MBON80vIky\",\n\"firstName\": \"Jane\", \"lastName\": \"Doe\", \"position\":\n\"Admin\", \"status\": \"Office\", \"phoneNumber\": \"1232531\",\n\"roles\": [ { \"role_id\": 2, \"role\": \"ADMIN\" } ], \"leaves\":\n[], \"startDate\": \"2018-03-20\" }]",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/uniqhorn/controller/UserController.java",
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
          "title": "Success-Example: { \"id\": 1, \"username\":",
          "content": "\"John@gmail.com\", \"password\":\n\"$2a$10$1mnewXZqa0m.V4peRkt2xuniYRC7bxu6.1cjFJ8SNx3MBON80vIky\",\n\"firstName\": \"John\", \"lastName\": \"Doe\", \"position\":\n\"Admin\", \"status\": \"Office\", \"phoneNumber\": \"1232531\",\n\"roles\": [ { \"role_id\": 2, \"role\": \"ADMIN\" } ], \"leaves\":\n[], \"startDate\": \"2018-03-20\" }",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "src/main/java/com/uniqhorn/controller/UserController.java",
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
          "title": "Request-Example: { \"username\" : \"test2@gmail.com\",",
          "content": "\"password\" : \"Test123\", \"firstName\": \"John\", \"lastName\" :\n\"Doe\", \"position\" : \"Tester\", \"status\" : \"inOffice\",\n\"phoneNumber\" : \"+359876278593\", \"startDate\" : \"2018-01-30\",\n\"roles\" : [{ \"role\":\"MASTER\" }] }",
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
    "filename": "src/main/java/com/uniqhorn/controller/UserController.java",
    "groupTitle": "User"
  },
  {
    "type": "delete",
    "url": "/work/:id",
    "title": "Delete work",
    "name": "deleteWork",
    "group": "Work",
    "permission": [
      {
        "name": "User"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "HTTP200OK",
            "description": "<p>ok ok</p>"
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
    "filename": "src/main/java/com/uniqhorn/controller/WorkController.java",
    "groupTitle": "Work"
  },
  {
    "type": "get",
    "url": "/work/username",
    "title": "Get work",
    "name": "getWork",
    "group": "Work",
    "permission": [
      {
        "name": "User"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "HTTP200OK",
            "description": "<p>ok ok</p>"
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
    "filename": "src/main/java/com/uniqhorn/controller/WorkController.java",
    "groupTitle": "Work"
  },
  {
    "type": "post",
    "url": "/work/username",
    "title": "Log work",
    "name": "logWork",
    "group": "Work",
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
            "field": "workDate",
            "description": "<p>Work date</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "workHours",
            "description": "<p>Work hours</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "workType",
            "description": "<p>Work type</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "client",
            "description": "<p>Client</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "HTTP200OK",
            "description": "<p>ok ok</p>"
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
    "filename": "src/main/java/com/uniqhorn/controller/WorkController.java",
    "groupTitle": "Work"
  },
  {
    "type": "patch",
    "url": "/work/:id",
    "title": "Patch work",
    "name": "patchWork",
    "group": "Work",
    "permission": [
      {
        "name": "User"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "HTTP200OK",
            "description": "<p>ok ok</p>"
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
    "filename": "src/main/java/com/uniqhorn/controller/WorkController.java",
    "groupTitle": "Work"
  }
] });
