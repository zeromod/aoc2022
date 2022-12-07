extern crate core;

use std::env;
use crate::solutions::*;

mod solutions;

fn main() {    let args: Vec<String> = env::args().collect();
    let day: u8 = match args.get(1) {
        Some(s) => s.parse::<u8>().unwrap(),
        None => 7,
    };
    match day {
        1 => day01::solve(),
        2 => day02::solve(),
        6 => day06::solve(),
        7 => day07::solve(),
        _ => panic!("day not solved: {day}"),
    }
    println!("🎄🌟🎄🌟🎄🎅☃️❄️🎄🌟🎄🌟🎄")
}
