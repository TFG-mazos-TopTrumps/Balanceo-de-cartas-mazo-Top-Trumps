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
fileNamePrefix <- "./RQ21-ImpactOfTheNumberOfCardsPerDeck/data/NSGAII/"
fileNameInterfix <- "/FUN"
deckSizes <-c(4,8,16,32,64,128)
RQ21 <- NA
# Data loading and crunching:
for(deckSize in deckSizes){
  for(i in 1:(nRuns-1)){
    auxData <- read_csv(paste0(fileNamePrefix,as.character(deckSize),fileNameInterfix,as.character(i),".csv"),col_names = FALSE)
    names(auxData)<- c("Closeness","Fairness","DominatedCards")
    auxData$Fairness<- -auxData$Fairness
    #auxSummaryDataPerRun <-auxData %>% summarise(
    #  maxFairness=max(Fairness),meanFairness= mean(Fairness),minFairness=min(Fairness),
    #  maxCloseness=max(Closeness),meanCloseness= mean(Closeness),minCloseness=min(Closeness),
    #  maxDominatedCards=max(DominatedCards),meanDominatedCards= mean(DominatedCards),minDominatedCards=min(DominatedCards),
    #)
    #auxSummaryDataPerRun$DeckSize <- deckSize
    auxData$DeckSize<- deckSize
    if(is.na(RQ21)){
       RQ21 <- auxData
    #   summaryDataPerRun <- auxSummaryDataPerRun
    }else{
      RQ21 <- rbind(RQ21,auxData)
    #  summaryDataPerRun <- rbind(summaryDataPerRun,auxSummaryDataPerRun)
    }
  }
}
names(RQ21)<- c("Closeness","Fairness","DominatedCards","DeckSize")



# Summary tables:
#tab<-capture.output(print(cbind(summary_table(RQ21),summary_table(RQ21,c("DeckSize")))))
#fileConn<-file("RQ21-SummaryTable.tex")
#writeLines(tab, fileConn)
#close(fileConn)

RQ21$DeckSize<-as.factor(RQ21$DeckSize)
plot <- ggplot(RQ21,aes(x=RQ21$DeckSize,y=Fairness))+
  geom_boxplot() +
  labs(x="Deck size (number of cards)")
ggsave(file="ImpactOfDeckSizeOnFairness.pdf",plot,device="pdf")

plot<- ggplot(RQ21,aes(x=DeckSize,y=Closeness))+
  geom_boxplot() +
  labs(x="Deck size (number of cards)")
ggsave(file="ImpactOfDeckSizeOnCloseness.pdf",plot,device="pdf")

plot <- ggplot(RQ21,aes(x=DeckSize,y=DominatedCards))+
  geom_boxplot() +
  labs(x="Deck size (number of cards)",y="Number of dominated cards")
ggsave(file="ImpactOfDeckSizeOnDominatedCards.pdf",plot,device="pdf")


#summaryDataFairness <- summaryDataPerRun %>%
#                          select(maxFairness,meanFairness,minFairness,DeckSize) %>%  
#                          pivot_longer(c("maxFairness","meanFairness","minFairness"), names_to = "Mesaures", values_to = "Fairness")

#summaryDataFairness$Mesaures <- as.factor(summaryDataFairness$Mesaures)                                                    
#ggplot(summaryDataFairness,aes(x=DeckSize,y=Fairness,color=summaryDataFairness$Mesaures))+
#  geom_point() +
#  labs(x="Deck size (number of cards)",y="fairness")
