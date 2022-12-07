package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
)

func main() {
    readFile, err := os.Open("input")
	if err != nil {
		fmt.Println(err)
	}
	fileScanner := bufio.NewScanner(readFile)

	fileScanner.Split(bufio.ScanLines)

	countingCalaries := 0
	var helpingElves []int

	for fileScanner.Scan() {
		calories := fileScanner.Text()
		if calories == "" {
			helpingElves = append(helpingElves, countingCalaries)
			countingCalaries = 0
		} else {
			c, _ := strconv.Atoi(calories)
			countingCalaries += c
		}
	}
	sort.Sort(sort.Reverse(sort.IntSlice(helpingElves)))
	fmt.Println(helpingElves[0])
	fmt.Println(helpingElves[0] + helpingElves[1] + helpingElves[2])

	_ = readFile.Close()
}
