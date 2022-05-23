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
fileNamePrefix <- "./RQ223-ImpactOfTheNumberOfCardsToChooseFrom/data/NSGAII/"
fileNameInterfix <- "/FUN"
deckSize <-30
CardsToChoose <- c(1,2,4,8,16,30)
RQ23 <- NA
# Data loading and crunching:
for(category in CardsToChoose){
  for(i in 1:(nRuns-1)){
    auxData <- read_csv(paste0(fileNamePrefix,as.character(category),fileNameInterfix,as.character(i),".csv"),col_names = FALSE)
    names(auxData)<- c("Closeness","Fairness","DominatedCards")
    auxData$Fairness<- -auxData$Fairness
    #auxSummaryDataPerRun <-auxData %>% summarise(
    #  maxFairness=max(Fairness),meanFairness= mean(Fairness),minFairness=min(Fairness),
    #  maxCloseness=max(Closeness),meanCloseness= mean(Closeness),minCloseness=min(Closeness),
    #  maxDominatedCards=max(DominatedCards),meanDominatedCards= mean(DominatedCards),minDominatedCards=min(DominatedCards),
    #)
    #auxSummaryDataPerRun$DeckSize <- deckSize
    auxData$CardsToChoose<- category
    if(is.na(RQ23)){
       RQ23 <- auxData
     #  summaryDataPerRun <- auxSummaryDataPerRun
    }else{
      RQ23 <- rbind(RQ23,auxData)
      #summaryDataPerRun <- rbind(summaryDataPerRun,auxSummaryDataPerRun)
    }
  }
}
names(RQ23)<- c("Closeness","Fairness","DominatedCards","CardsToChoose")



# Summary tables:
RQ23$CardsToChoose<-as.factor(RQ23$CardsToChoose)
#tab<-capture.output(print(cbind(summary_table(RQ23),summary_table(RQ23,c("CardsToChoose")))))
#fileConn<-file("RQ23-SummaryTable.tex")
#writeLines(tab, fileConn)
#close(fileConn)


plot <- ggplot(RQ23,aes(x=CardsToChoose,y=Fairness))+
  geom_boxplot() +
  labs(x="Number of cards to choose from")
ggsave(file="ImpactOfCardsToChooseOnFairness.pdf",plot,device="pdf")

plot<- ggplot(RQ23,aes(x=CardsToChoose,y=Closeness))+
  geom_boxplot() +
  labs(x="Number of cards to choose from")
ggsave(file="ImpactOfCardsToChooseOnCloseness.pdf",plot,device="pdf")

plot <- ggplot(RQ23,aes(x=RQ23$CardsToChoose,y=DominatedCards))+
  geom_boxplot() +
  labs(x="Number of cards to choose from",y="Number of dominated cards")
ggsave(file="ImpactOfCardsToChooseOnDominatedCards.pdf",plot,device="pdf")


#summaryDataFairness <- summaryDataPerRun %>%
#                          select(maxFairness,meanFairness,minFairness,DeckSize) %>%  
#                          pivot_longer(c("maxFairness","meanFairness","minFairness"), names_to = "Mesaures", values_to = "Fairness")

#summaryDataFairness$Mesaures <- as.factor(summaryDataFairness$Mesaures)                                                    
#ggplot(summaryDataFairness,aes(x=DeckSize,y=Fairness,color=summaryDataFairness$Mesaures))+
#  geom_point() +
#  labs(x="Deck size (number of cards)",y="fairness")
