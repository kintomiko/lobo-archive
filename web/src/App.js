import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Game from './components/game'

class App extends Component {
  constructor(){
      this.state = {
        currentState: null,
        history: null,
        currentStep: null,
        width: null,
        height: null
      } 
  }

  render() {
    return (
      <Game width={this.state.width} height={this.state.height} 
        currentState={this.state.currentState} 
        currentStep={this.state.currentStep} 
        history={this.state.history}/>
    );
  }
}

export default App;
