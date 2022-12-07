use itertools::Itertools;

pub fn solve() {
    let input = include_str!("../inputs/input1");
    let calories = input.split("\n\n")
        .map(|calories| calories.split("\n").map(|c| c.parse::<u32>().unwrap()).sum::<u32>())
        .sorted_by(|a, b| b.cmp(a)).collect_vec();
    let max = calories.first().unwrap().clone();
    assert_eq!(67658, max);
    let max3 = calories.iter().take(3).sum::<u32>();
    assert_eq!(200158, max3);
}