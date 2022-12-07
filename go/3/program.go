package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	readFile, err := os.Open("input")
	if err != nil {
		fmt.Println(err)
	}
	fileScanner := bufio.NewScanner(readFile)

	fileScanner.Split(bufio.ScanLines)

	sumOfDuplicateItems := 0
	sumOfBadge := 0
	lines := [][]int{}
	index := 0
	for fileScanner.Scan() {
		line := fileScanner.Text()
		var input []int
		for i := range line {
			input = append(input, int(line[i]))
		}
		lines = append(lines, input)
		sack1 := input[0:(len(input) / 2)]
		sack2 := input[len(input)/2:]

		mixedSack := Intersection(sack1, sack2)
		sumOfDuplicateItems += SumOfPriorities(mixedSack)
		index++
	}
	for i := 0; i <= len(lines)-1; i += 3 {
		abMap := Intersection(lines[i], lines[i+1])
		ab := make([]int, 0, len(abMap))

		for _, value := range abMap {
			ab = append(ab, value)
		}
		mixedSack := Intersection(ab, lines[i+2])
		sumOfBadge += SumOfPriorities(mixedSack)
	}

	fmt.Println(sumOfDuplicateItems)
	fmt.Println(sumOfBadge)
	_ = readFile.Close()
}

func SumOfPriorities(a map[int]int) int {
	sum := 0
	for i := range a {
		item := a[i]
		if item >= 65 && item <= 90 {
			sum += item - 38
		} else if item >= 97 && item <= 122 {
			sum += item - 96
		}
	}
	return sum
}

func Intersection(a, b []int) (c map[int]int) {
	m := make(map[int]bool)
	c = make(map[int]int)

	for _, item := range a {
		m[item] = true
	}

	for _, item := range b {
		if _, ok := m[item]; ok {
			if c[item] == 0 {
				c[item] = item
			}
		}
	}
	return
}
