# Notes on Java exceptions
---
A section for notes on how to use exceptions.
> Author: Omgitskuei
> 
> Last Updated: 2022/05/20
> 
> Status: n/a

### Printing Exceptions
---
See [SysoutException.java][linkPrintingExceptions] for getting and printing information about the Exception, most useful being name of Exception.
Note, this is different from getting the StaceTrace.


### Differentiating Exceptions
---
See [DifferentiatingExceptionType.java][linkDiffExceptionType] for a sample of a try-catch with multiple catches for each type of Exception thrown.
Note, the catches need to be ordered from smallest scope to largest - the last catch could be "catch (Exception e)" to 
catch anything else that was not previously caught. The inverse shouldn't be done because catching Exception at the beginning 
turns later catches into Dead Code. 


### Catching multiple Exceptions in one catch
---
See [MultipleExceptionsInOneCatch.java][linkCatchingMultiple] for more on the syntax for joining Exceptions in the same catch.
In short, Exceptions are joined with "|" like so; "catch(NullPointerException | NumberFormatException e)".
When the way in which exceptions are handled are the same across multiple catches, it　is possible to handle multiple exceptions
with the same catch. This shortens the code and generally improves readability.


   [linkDiffExceptionType]:
<https://github.com/omgitskuei/JavaDev/blob/master/src/main/notes/exceptions/DifferentiatingExceptionType.java>

   [linkPrintingExceptions]:
<https://github.com/omgitskuei/JavaDev/blob/master/src/main/notes/exceptions/PrintingExceptions.java>

   [linkCatchingMultiple]:
<https://github.com/omgitskuei/JavaDev/blob/master/src/main/notes/exceptions/MultipleExceptionsInOneCatch.java>