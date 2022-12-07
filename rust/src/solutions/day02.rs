use itertools::Itertools;

pub fn solve() {
    let mut score = 0;
    let mut score2 = 0;

    let input = include_str!("../inputs/input2");
    input.split("\n")
        .map(|line| line.split(" ").collect_tuple().unwrap())
        .for_each(|(opponent, response): (&str, &str)| {
            match opponent {
                "A" => match response {
                    "X" => {
                        score += 4;
                        score2 += 3;
                    }
                    "Y" => {
                        score += 8;
                        score2 += 4;
                    }

                    "Z" => {
                        score += 3;
                        score2 += 8;
                    }
                    _ => {}
                }

                "B" => match response {
                    "X" => {
                        score += 1;
                        score2 += 1;
                    }

                    "Y" => {
                        score += 5;
                        score2 += 5;
                    }

                    "Z" => {
                        score += 9;
                        score2 += 9;
                    }
                    _ => {}
                }

                "C" => match response {
                    "X" => {
                        score += 7;
                        score2 += 2
                    }

                    "Y" => {
                        score += 2;
                        score2 += 6
                    }

                    "Z" => {
                        score += 6;
                        score2 += 7
                    }
                    _ => {}
                },
                _ => {}
            }
        });

    assert_eq!(14531, score);
    assert_eq!(11258, score2);
}
