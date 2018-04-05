# wheel-of-fate
[![Build Status](https://travis-ci.org/chinmaykulkarnee/wheel-of-fate.svg?branch=master)](https://travis-ci.org/chinmaykulkarnee/wheel-of-fate)

## Getting Started:
#### Dependencies
- Application uses junit, mockito for testing and lomobok
- All dependencies will be handled by ```build.gradle``` file

#### Build
- Run ```./gradlew clean build``` to run the tests and build the project

#### Run
- Run ```./gradlew bootrun```
- Above command start the application on localhost:8080

#### Sample Response 
- Schedule for 2 days with 2 engineers with 1 shift per day:
- Request : http://localhost:8080/schedules?batchSizeInDays=2&availableEngineers=2&shiftsPerDay=1&maximumShiftsForEngineersInABatch=1
```
{
  "days": [
    {
      "shifts": [
        {
          "engineer": {
            "name": "Engineer1",
            "allocationCount": 1
          }
        }
      ],
      "name": "Day1"
    },
    {
      "shifts": [
        {
          "engineer": {
            "name": "Engineer2",
            "allocationCount": 1
          }
        }
      ],
      "name": "Day2"
    }
  ],
  "size": 2,
  "message": "Time taken to build the schedule is : 2ms"
}
```

#### API available
/schedules
- Takes following query params as input
    - batchSizeInDays - input batch size (2 weeks represents 10 days) for which schedule has to be prepared
    - availableEngineers - total number of engineers available 
    - shiftsPerDay - total shifts in a day
    - maximumShiftsForEngineersInABatch - maximum number that an engg can do in a batch

## Project overview:
#### Models
- Engineer - represents an engineer with name and allocation count
- Shift - represents shift in a day which will have a numberInDay(e.g. 1 or 2) and engineer allocated to it
- Day - represents day in a schedule which will list of shifts
- Schedule 
    - represents whole schedule with list of days 
    - also contains some global config variables
    - passed between services as current global state
    - each new day scheduled is appended at the end of list of days   

#### Flow
- When user requests to generate schedule with parameters, 
    - We first create a pool of available engineers and set allocationCount=0 for each engineer
    - Then we go on building the schedule recursively by,
        - picking up a available engineer from pool and assigning it to shift and assigning that shift to day
        - engineer is picked at random from pool
    - Picking up engineer at random may cause schedule generation to fail in the end since no valid available engineer will be available
    - This will be handled by backtracking algorithms which frees up last few days of schedule and start generation process again  

## Misc Notes:
- Tests are written in pragmatic way, only for the classes which have some logic
- Lombok annotations are used for simplicity, so if you are using an IDE you may need to enable Annotation Processing option 