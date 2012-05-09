wicket-select2
==============

Wicket components and behaviors for [Select2](https://github.com/ivaynberg/select2).
These classes expose all Select2 use cases: single or multiple selects, and select
options derived from HTML or AJAX queries.

The Maven project builds a JAR with the one behavior and two components. The test code
includes Select2DemoApplication which is a simple Wicket application whose public
static main() starts it in an embedded Jetty instance at http://localhost:8081. The
home page, Select2DemoPage, shows various ways you can use Select2 to decorate your
components.

Existing DropDownChoice and ListMultipleChoice components in your application can be
Select2-ified with a single line of code:
    component.add(Select2Behavior.forChoice(component)).
    
To filter and populate select options with AJAX queries, use Select2SingleChoice or
Select2MultipleChoice. Each of these requires an ISelect2AjaxAdapter instance which
implements the query logic and id/display mapping. See StateAdapter (from the demo) and
the Select2TagAdapter for simple examples.

Please note: Select2 requires jQuery. The Select2Behavior does not import jQuery.
You need to do this yourself. (I didn't want to impose a specific version and hosted
location on you.)

Feedback is welcome.

Copyright and License
---------------------

Copyright 2012 Dan Retzlaff

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
