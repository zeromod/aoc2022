use std::collections::HashSet;
use itertools::Itertools;

pub fn solve() {
    let input = include_str!("../inputs/input6")
        .lines()
        .next().unwrap()
        .chars()
        .collect_vec();
    find_header(&input, 4);
    find_header(&input, 14);
}

fn find_header(input: &Vec<char>, size: usize) {
    input.windows(size).enumerate().find(|(i, part)| {
        let signal: HashSet<&char> = HashSet::from_iter(part.iter());
        return if signal.len() == size {
            match size {
                4 => {
                    assert_eq!(1702, i + 4)
                }
                14 => {
                    assert_eq!(3559, i + 14);
                }
                _ => {}
            }
            true
        } else {
            false
        };
    }).unwrap();
}
