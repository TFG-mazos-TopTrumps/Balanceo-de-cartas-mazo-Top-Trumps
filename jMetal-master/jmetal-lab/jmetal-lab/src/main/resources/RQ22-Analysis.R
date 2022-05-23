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
fileNamePrefix <- "./RQ22-ImpactOfTheNumberOfCategories/data/NSGAII/"
fileNameInterfix <- "/FUN"
deckSize <-30
categories <- c(2,4,8,16)
RQ22 <- NA
# Data loading and crunching:
for(category in categories){
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
    auxData$Categories<- category
    if(is.na(RQ22)){
       RQ22 <- auxData
     #  summaryDataPerRun <- auxSummaryDataPerRun
    }else{
      RQ22 <- rbind(RQ22,auxData)
      #summaryDataPerRun <- rbind(summaryDataPerRun,auxSummaryDataPerRun)
    }
  }
}
names(RQ22)<- c("Closeness","Fairness","DominatedCards","Categories")



# Summary tables:
RQ22$Categories<-as.factor(RQ22$Categories)
#tab<-capture.output(print(cbind(summary_table(RQ22),summary_table(RQ22,c("Categories")))))
#fileConn<-file("RQ22-SummaryTable.tex")
#writeLines(tab, fileConn)
#close(fileConn)


plot <- ggplot(RQ22,aes(x=Categories,y=Fairness))+
  geom_boxplot() +
  labs(x="Number of categories per card")
ggsave(file="ImpactOfCategoriesOnFairnessBis.pdf",plot,device="pdf")

plot<- ggplot(RQ22,aes(x=Categories,y=Closeness))+
  geom_boxplot() +
  labs(x="Number of categories per card")
ggsave(file="ImpactOfCategoriesOnClosenessBis.pdf",plot,device="pdf")

plot <- ggplot(RQ22,aes(x=RQ22$Categories,y=DominatedCards))+
  geom_boxplot() +
  labs(x="Number of categories per card",y="Number of dominated cards")
ggsave(file="ImpactOfCategoriesOnDominatedCardsBis.pdf",plot,device="pdf")


#summaryDataFairness <- summaryDataPerRun %>%
#                          select(maxFairness,meanFairness,minFairness,DeckSize) %>%  
#                          pivot_longer(c("maxFairness","meanFairness","minFairness"), names_to = "Mesaures", values_to = "Fairness")

#summaryDataFairness$Mesaures <- as.factor(summaryDataFairness$Mesaures)                                                    
#ggplot(summaryDataFairness,aes(x=DeckSize,y=Fairness,color=summaryDataFairness$Mesaures))+
#  geom_point() +
#  labs(x="Deck size (number of cards)",y="fairness")
