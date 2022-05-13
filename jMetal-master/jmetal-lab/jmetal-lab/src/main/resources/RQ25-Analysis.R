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
Players <- 15
games <- 30
Players <- 15
fileNamePrefix <- "./RQ25-ImpactOfTheNumberOfPlayers/data/NSGAII/"
fileNameInterfix <- "/FUN"
deckSize <-30
RQ25 <- NA
# Data loading and crunching:
for(players in c(2,3,5,6)){
  for(i in 1:(nRuns-1)){
    auxData <- read_csv(paste0(fileNamePrefix,as.character(players),fileNameInterfix,as.character(i),".csv"),col_names = FALSE)
    names(auxData)<- c("Closeness","Fairness","DominatedCards")
    auxData$Fairness<- -auxData$Fairness
    #auxSummaryDataPerRun <-auxData %>% summarise(
    #  maxFairness=max(Fairness),meanFairness= mean(Fairness),minFairness=min(Fairness),
    #  maxCloseness=max(Closeness),meanCloseness= mean(Closeness),minCloseness=min(Closeness),
    #  maxDominatedCards=max(DominatedCards),meanDominatedCards= mean(DominatedCards),minDominatedCards=min(DominatedCards),
    #)
    #auxSummaryDataPerRun$DeckSize <- deckSize
    auxData$Players<- players
    if(is.na(RQ25)){
       RQ25 <- auxData
     #  summaryDataPerRun <- auxSummaryDataPerRun
    }else{
      RQ25 <- rbind(RQ25,auxData)
      #summaryDataPerRun <- rbind(summaryDataPerRun,auxSummaryDataPerRun)
    }
  }
}
names(RQ25)<- c("Closeness","Fairness","DominatedCards","Players")



# Summary tables:
RQ25$Players<-as.factor(RQ25$Players)
#tab<-capture.output(print(cbind(summary_table(RQ25),summary_table(RQ25,c("Players")))))
#fileConn<-file("RQ25-SummaryTable.tex")
#writeLines(tab, fileConn)
#close(fileConn)


plot <- ggplot(RQ25,aes(x=Players,y=Fairness))+
  geom_boxplot() +
  labs(x="Number of players")
ggsave(file="ImpactOfPlayersOnFairness.pdf",plot,device="pdf")

plot<- ggplot(RQ25,aes(x=Players,y=Closeness))+
  geom_boxplot() +
  labs(x="Number of players")
ggsave(file="ImpactOfPlayersOnCloseness.pdf",plot,device="pdf")

plot <- ggplot(RQ25,aes(x=RQ25$Players,y=DominatedCards))+
  geom_boxplot() +
  labs(x="Number of players",y="Number of dominated cards")
ggsave(file="ImpactOfPlayersOnDominatedCards.pdf",plot,device="pdf")


#summaryDataFairness <- summaryDataPerRun %>%
#                          select(maxFairness,meanFairness,minFairness,DeckSize) %>%  
#                          pivot_longer(c("maxFairness","meanFairness","minFairness"), names_to = "Mesaures", values_to = "Fairness")

#summaryDataFairness$Mesaures <- as.factor(summaryDataFairness$Mesaures)                                                    
#ggplot(summaryDataFairness,aes(x=DeckSize,y=Fairness,color=summaryDataFairness$Mesaures))+
#  geom_point() +
#  labs(x="Deck size (number of cards)",y="fairness")
