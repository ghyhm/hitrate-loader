$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("loadHitrate.feature");
formatter.feature({
  "line": 1,
  "name": "Load hit rate data",
  "description": "",
  "id": "load-hit-rate-data",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Search top 5 hit rate websites",
  "description": "",
  "id": "load-hit-rate-data;search-top-5-hit-rate-websites",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "I have the following hit rate data",
  "rows": [
    {
      "cells": [
        "visit_date",
        "website",
        "visits"
      ],
      "line": 6
    },
    {
      "cells": [
        "2016-01-06",
        "www.bing.com",
        "14000000"
      ],
      "line": 7
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "hit rate loader is run",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "the following hit rate data should be loaded",
  "rows": [
    {
      "cells": [
        "visit_date",
        "website",
        "visits"
      ],
      "line": 10
    },
    {
      "cells": [
        "2016-01-06",
        "www.bing.com",
        "14065457"
      ],
      "line": 11
    },
    {
      "cells": [
        "2016-01-06",
        "www.ebay.com.au",
        "19831166"
      ],
      "line": 12
    },
    {
      "cells": [
        "2016-01-06",
        "www.facebook.com",
        "104346720"
      ],
      "line": 13
    },
    {
      "cells": [
        "2016-01-06",
        "mail.live.com",
        "21536612"
      ],
      "line": 14
    },
    {
      "cells": [
        "2016-01-06",
        "www.wikipedia.org",
        "13246531"
      ],
      "line": 15
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "LoadHitrateSteps.i_have_the_following_hit_rate_data(DataTable)"
});
formatter.result({
  "duration": 76809466,
  "error_message": "cucumber.api.PendingException: TODO: implement me\n\tat cucumber.steps.LoadHitrateSteps.i_have_the_following_hit_rate_data(LoadHitrateSteps.java:16)\n\tat ✽.Given I have the following hit rate data(loadHitrate.feature:5)\n",
  "status": "pending"
});
formatter.match({
  "location": "LoadHitrateSteps.hit_rate_loader_is_run()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "LoadHitrateSteps.the_following_hit_rate_data_should_be_loaded(DataTable)"
});
formatter.result({
  "status": "skipped"
});
});