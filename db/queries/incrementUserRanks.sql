UPDATE rank
LEFT JOIN user_word ON rank.user_word_id = user_word.id
SET rank.rank_value = rank.rank_value + 1
WHERE user_word.user_id = 1
