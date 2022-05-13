# Author: José Antonio Parejo Maestre
# 
# Object: Anaysis of restuls of Experiment 1 of
# RQ1: 
#Libraries:
library(readr)
library(dplyr)
library(tidyr)
library(ggplot2)
library(qwraps2)
#Global parameters and options:
options(qwraps2_markup = "latex")

nRuns <- 10
players <- 2
games <- 30
rounds <- 15
fileNamePrefix <- "./RQ24-ImpactOfTheNumberOfRounds/data/NSGAII/"
fileNameInterfix <- "/FUN"
deckSize <-30
Rounds <- 3
RQ24 <- NA
# Data loading and crunching:
for(rounds in 5:29){
  for(i in 1:(nRuns-1)){
    auxData <- read_csv(paste0(fileNamePrefix,as.character(rounds),fileNameInterfix,as.character(i),".csv"),col_names = FALSE)
    names(auxData)<- c("Closeness","Fairness","DominatedCards")
    auxData$Fairness<- -auxData$Fairness
    #auxSummaryDataPerRun <-auxData %>% summarise(
    #  maxFairness=max(Fairness),meanFairness= mean(Fairness),minFairness=min(Fairness),
    #  maxCloseness=max(Closeness),meanCloseness= mean(Closeness),minCloseness=min(Closeness),
    #  maxDominatedCards=max(DominatedCards),meanDominatedCards= mean(DominatedCards),minDominatedCards=min(DominatedCards),
    #)
    #auxSummaryDataPerRun$DeckSize <- deckSize
    auxData$Rounds<- rounds
    if(is.na(RQ24)){
       RQ24 <- auxData
     #  summaryDataPerRun <- auxSummaryDataPerRun
    }else{
      RQ24 <- rbind(RQ24,auxData)
      #summaryDataPerRun <- rbind(summaryDataPerRun,auxSummaryDataPerRun)
    }
  }
}
names(RQ24)<- c("Closeness","Fairness","DominatedCards","Rounds")



# Summary tables:
RQ24$Rounds<-as.factor(RQ24$Rounds)
#tab<-capture.output(print(cbind(summary_table(RQ24),summary_table(RQ24,c("Rounds")))))
#fileConn<-file("RQ24-SummaryTable.tex")
#writeLines(tab, fileConn)
#close(fileConn)


plot <- ggplot(RQ24,aes(x=Rounds,y=Fairness))+
  geom_boxplot() +
  labs(x="Number of rounds")
ggsave(file="ImpactOfRoundsOnFairness.pdf",plot,device="pdf")

plot<- ggplot(RQ24,aes(x=Rounds,y=Closeness))+
  geom_boxplot() +
  labs(x="Number of rounds")
ggsave(file="ImpactOfRoundsOnCloseness.pdf",plot,device="pdf")

plot <- ggplot(RQ24,aes(x=RQ24$Rounds,y=DominatedCards))+
  geom_boxplot() +
  labs(x="Number of rounds",y="Number of dominated cards")
ggsave(file="ImpactOfRoundsOnDominatedCards.pdf",plot,device="pdf")


#summaryDataFairness <- summaryDataPerRun %>%
#                          select(maxFairness,meanFairness,minFairness,DeckSize) %>%  
#                          pivot_longer(c("maxFairness","meanFairness","minFairness"), names_to = "Mesaures", values_to = "Fairness")

#summaryDataFairness$Mesaures <- as.factor(summaryDataFairness$Mesaures)                                                    
#ggplot(summaryDataFairness,aes(x=DeckSize,y=Fairness,color=summaryDataFairness$Mesaures))+
#  geom_point() +
#  labs(x="Deck size (number of cards)",y="fairness")
