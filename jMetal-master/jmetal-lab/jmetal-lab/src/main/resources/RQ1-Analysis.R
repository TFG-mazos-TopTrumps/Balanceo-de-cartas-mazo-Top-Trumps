# Author: José Antonio Parejo Maestre
# 
# Object: Anaysis of restuls of Experiment 1 of
# RQ1: 
#Libraries:
library(readr)
library(qwraps2)
#Global parameters and options:
options(qwraps2_markup = "latex")

nRuns <- 30
players <- 2
games <- 30
rounds <- 15
fileNamePrefix <- "./RQ1-MazoTopTrumpsTriObjetivoStudy/data/NSGAII/TopTrumpsDeckTriObjective/FUN"

# Data loading and crunching:

RQ1 <- read_csv(paste0(fileNamePrefix,"0",".csv"),col_names = FALSE)
for(i in 1:(nRuns-1)){
  RQ1 <- rbind(RQ1,read_csv(paste0(fileNamePrefix,as.character(i),".csv"),col_names = FALSE))
}

names(RQ1)<- c("Closeness","Fairness","Dominated Cards")

RQ1$Fairness<- -RQ1$Fairness

tab<-capture.output(print(summary_table(RQ1)))
fileConn<-file("RQ1-SummaryTable.tex")
writeLines(tab, fileConn)
close(fileConn)
