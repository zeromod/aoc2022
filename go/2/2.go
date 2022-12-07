package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	readFile, err := os.Open("input")
	if err != nil {
		fmt.Println(err)
	}
	fileScanner := bufio.NewScanner(readFile)

	fileScanner.Split(bufio.ScanLines)

	score := 0
	score2 := 0
	for fileScanner.Scan() {
		input := strings.Split(fileScanner.Text(), " ")
		opponent := input[0]
		response := input[1]
		switch opponent {
		case "A":
			switch response {
			case "X":
				score += 4
				score2 += 0 + 3
			case "Y":
				score += 8
				score2 += 3 + 1
			case "Z":
				score += 3
				score2 += 6 + 2

			}
		case "B":
			switch response {
			case "X":
				score += 1
				score2 += 0 + 1
			case "Y":
				score += 5
				score2 += 3 + 2
			case "Z":
				score += 9
				score2 += 6 + 3

			}
		case "C":
			switch response {
			case "X":
				score += 7
				score2 += 0 + 2
			case "Y":
				score += 2
				score2 += 3 + 3
			case "Z":
				score += 6
				score2 += 6 + 1

			}
		}
	}
	fmt.Println(score)
	fmt.Println(score2)

	_ = readFile.Close()
}
