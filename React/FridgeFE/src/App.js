import React from 'react';
import Fridge from './Fridge';
import Service from './Service';
import Results from './Results';
import './CSS/App.css'
import {Container, Grid} from 'semantic-ui-react'

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {

    }
  }

  render() {
    return (
      <Container>
        <Service/>
      </Container>
    );
  }
}


export default App;
