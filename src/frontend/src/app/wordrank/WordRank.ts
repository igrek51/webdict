export class WordRank {
  wordName: string;
  definition: string;
  rankValue: number;
  triesCount: number;

  constructor(wordName, definition, rankValue, triesCount) {
    this.wordName = wordName;
    this.definition = definition;
    this.rankValue = rankValue;
    this.triesCount = triesCount;
  }
}
