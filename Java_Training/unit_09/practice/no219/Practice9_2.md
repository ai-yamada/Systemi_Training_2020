1.  最初のcatchブロックしか呼び出さない
2.  具体的な例外の内容を識別できるよう、汎用的なExceptionのスローは避ける
    回復可能な例外は検査例外で、さもなければ非検査例外として投げる
    標準的な例外が用意されているものは、独自例外よりも標準例外を利用する。