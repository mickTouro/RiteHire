# RiteHire
Solution for coding challenge.

Assume the days of current year is numbered 1 to 365. 
A Coverage is defined as range of dates with a coverage start and end/term date. Eg: Cov(1, 30) means the person is covered for something for Jan of this year.
Problem: Given a series of coverage data for a person, we need to find the longest continuous coverage. The coverage may have overlap and/or gaps in coverage.
 
Example data: 
class Cov(eff: Int, term: Int)

val coverages = List(
  Cov(1, 20), Cov(21, 30), Cov(15, 25), Cov(28, 40), Cov(50, 60), Cov(61, 200)
)
 
Solution for example date is 50, 200 
